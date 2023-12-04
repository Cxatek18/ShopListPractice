package com.team.shoplistpr.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem): Unit
    fun deleteShopItem(shopItem: ShopItem): Unit
    fun editShopItem(shopItem: ShopItem): Unit
    fun getShopItem(shopItemId: Int): ShopItem
    fun getShopList(): List<ShopItem>
}