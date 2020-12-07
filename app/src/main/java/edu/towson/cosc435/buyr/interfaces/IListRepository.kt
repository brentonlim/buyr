package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.Item
import edu.towson.cosc435.buyr.model.List

interface IListRepository {
    fun getListCount(): Int
    fun getList(idx: Int): List
    suspend fun getLists(): kotlin.collections.List<List>
    suspend fun deleteList(idx: Int)
    suspend fun replaceList(idx: Int, list: List)
    suspend fun addList(list: List)
    suspend fun refreshLists()
}