package com.team.shoplistpr.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem): Unit{
        shopListRepository.addShopItem(shopItem)
    }
}