package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.Item

interface IItemsModel {
    fun getItemsCount(): Int
    suspend fun getItems(): List<Item>
    fun getItem(idx: Int): Item
    suspend fun deleteItem(idx: Int)
    suspend fun addItem(item: Item)
}