package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.Item

interface IItemRepository {
    fun getCount(): Int
    fun getItem(idx: Int): Item
    suspend fun getItems(): List<Item>
    suspend fun deleteItem(idx: Int)
    suspend fun addItem(item: Item)
    suspend fun refreshList()
}