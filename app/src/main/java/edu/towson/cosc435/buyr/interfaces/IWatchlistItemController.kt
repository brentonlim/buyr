package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.Item

interface IWatchlistItemController {
    suspend fun deleteWatchItem(idx: Int)
    fun getWatchItem(idx: Int): Item
    suspend fun getWatchItems(): List<Item>
    fun getWatchItemsCount(): Int
    suspend fun addNewWatchItem(item: Item)
    fun launchAddNewWatchItemScreen()

    // run a block of code asynchronously
    fun runAsync(blk: suspend () -> Unit)
}