package com.team.shoplistpr.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.team.shoplistpr.domain.ShopItem
import com.team.shoplistpr.domain.ShopListRepository
import java.util.TreeSet
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList: TreeSet<ShopItem> = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrementId: Int = 0
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    init {
        for(i in 0 until 100){
            val item: ShopItem = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement: ShopItem = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}