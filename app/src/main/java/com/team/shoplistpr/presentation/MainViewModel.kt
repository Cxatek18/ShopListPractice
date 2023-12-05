package com.team.shoplistpr.presentation

import androidx.lifecycle.ViewModel
import com.team.shoplistpr.data.ShopListRepositoryImpl
import com.team.shoplistpr.domain.DeleteShopItemUseCase
import com.team.shoplistpr.domain.EditShopItemUseCase
import com.team.shoplistpr.domain.GetShopListUseCase
import com.team.shoplistpr.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopIte(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem: ShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}