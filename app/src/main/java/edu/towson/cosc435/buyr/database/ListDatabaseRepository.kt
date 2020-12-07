package edu.towson.cosc435.buyr.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import edu.towson.cosc435.buyr.interfaces.IListRepository
import edu.towson.cosc435.buyr.model.List
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ListDatabaseRepository(ctx: Context) : IListRepository {
    private val lists: MutableList<List> = mutableListOf()
    private val db: ListDatabase = Room.databaseBuilder(
        ctx,
        ListDatabase::class.java,
        "lists.db"
    ).build()
    companion object {
        val mutex = Mutex()
    }

    override fun getCount(): Int {
        Log.d("ListsAdapter", "SIZE: ${lists.size}")
        return lists.size
    }

    override fun getList(idx: Int): List {
        return lists[idx]
    }

    override suspend fun getLists(): kotlin.collections.List<List> {
        if (getCount() == 0) {
            clearAndFillLists()
        }
        return lists
    }

    override suspend fun deleteList(idx: Int) {
        delay(2000)
        val list = lists[idx]
        db.listDao().deleteList(list)
        clearAndFillLists()
    }

    override suspend fun replaceList(idx: Int, list: List) {
        delay(2000)
        db.listDao().updateList(list)
        clearAndFillLists()
    }

    override suspend fun addList(list: List) {
        db.listDao().addList(list)
        clearAndFillLists()
    }

    private suspend fun clearAndFillLists() {
        mutex.withLock {
            lists.clear()
            db.listDao().getAllLists()?.let { lists.addAll(it) }
        }
    }

    override suspend fun refreshList() {
        clearAndFillLists()
    }
}
