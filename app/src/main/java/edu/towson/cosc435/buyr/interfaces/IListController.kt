package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.List

interface IListController {
    suspend fun deleteList(idx: Int)
    fun launchAddListScreen()
    fun getList(idx: Int): List
    suspend fun getLists(): kotlin.collections.List<List>
    fun getListsCount(): Int
    fun editList(idx: Int)
    suspend fun addNewList(list: List)
    fun getListForEdit(): List?
    // run a block of code asynchronously
    fun runAsync(blk: suspend () -> Unit)
}