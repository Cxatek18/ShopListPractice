package com.team.shoplistpr.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem): Unit
    fun deleteShopItem(shopItem: ShopItem): Unit
    fun editShopItem(shopItem: ShopItem): Unit
    fun getShopItem(shopItemId: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}