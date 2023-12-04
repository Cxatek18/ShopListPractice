package com.team.shoplistpr.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem): Unit{
        shopListRepository.editShopItem(shopItem)
    }
}