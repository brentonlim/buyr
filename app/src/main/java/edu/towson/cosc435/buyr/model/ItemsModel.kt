package edu.towson.cosc435.buyr.model

import edu.towson.cosc435.buyr.interfaces.IItemsModel
import edu.towson.cosc435.buyr.interfaces.IItemRepository
import kotlin.collections.List

class ItemsModel(private val itemRepository: IItemRepository): IItemsModel {
    override fun getItemsCount(): Int {
        return itemRepository.getItemCount()
    }

    override suspend fun getItems(): List<Item> {
        return itemRepository.getItems()
    }

    override fun getItem(idx: Int): Item {
        return itemRepository.getItem(idx)
    }

    override suspend fun deleteItem(idx: Int) {
        itemRepository.deleteItem(idx)
    }

    override suspend fun addItem(item: Item) {
        itemRepository.addItem(item)
    }
}