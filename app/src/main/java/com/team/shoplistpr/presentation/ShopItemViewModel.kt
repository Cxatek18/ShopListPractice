package com.team.shoplistpr.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.shoplistpr.data.ShopListRepositoryImpl
import com.team.shoplistpr.domain.AddShopItemUseCase
import com.team.shoplistpr.domain.EditShopItemUseCase
import com.team.shoplistpr.domain.GetShopItemUseCase
import com.team.shoplistpr.domain.ShopItem

class ShopItemViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _closedScreen = MutableLiveData<Unit>()
    val closedScreen: LiveData<Unit>
        get() = _closedScreen

    fun getShopItem(shopItemId: Int){
        val item: ShopItem = getShopListUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val nameItem = parseName(inputName)
        val countItem = parseCount(inputCount)
        val falidFields = validInput(nameItem, countItem)
        if(falidFields){
            val shopItem = ShopItem(nameItem, countItem, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val nameItem = parseName(inputName)
        val countItem = parseCount(inputCount)
        val falidFields = validInput(nameItem, countItem)
        if(falidFields){
            _shopItem.value?.let {
                val item = it.copy(name = nameItem, count = countItem)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(name: String?): String{
        return name?.trim() ?: ""
    }

    private fun parseCount(count: String?): Int{
        return try{
            count?.trim()?.toInt() ?: 0
        }catch (e: Exception){
            0
        }
    }

    private fun validInput(name: String, count: Int): Boolean{
        var result: Boolean = true
        if(name.isBlank()){
            _errorInputName.value = false
            result = false
        }
        if(count <= 0){
            _errorInputCount.value = false
            result = false
        }
        return result
    }

    public fun resetErrorInputName(){
        _errorInputName.value = true
    }

    public fun resetErrorInputCount(){
        _errorInputCount.value = true
    }

    private fun finishWork(){
        _closedScreen.value = Unit
    }
}