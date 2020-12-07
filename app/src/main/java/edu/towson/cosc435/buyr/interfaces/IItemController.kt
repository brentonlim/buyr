package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.Item

interface IItemController {
    suspend fun deleteItem(idx: Int)
    fun getItem(idx: Int): Item
    suspend fun getItems(): kotlin.collections.List<Item>
    fun getItemsCount(): Int
    suspend fun addNewItem(item: Item)
    fun launchAddNewItemScreen()

    // run a block of code asynchronously
    fun runAsync(blk: suspend () -> Unit)
}