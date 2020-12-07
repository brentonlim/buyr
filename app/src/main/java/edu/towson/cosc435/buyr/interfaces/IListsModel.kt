package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.List

interface IListsModel {
    fun getListsCount(): Int
    suspend fun getLists(): kotlin.collections.List<List>
    fun getList(idx: Int): List
    suspend fun deleteList(idx: Int)
    suspend fun addList(list: List)
    fun editList(idx: Int): List
    fun clearEdit()
}