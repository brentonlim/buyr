package edu.towson.cosc435.buyr.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import edu.towson.cosc435.buyr.interfaces.IItemRepository
import edu.towson.cosc435.buyr.model.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ItemDatabaseRepository(ctx: Context) : IItemRepository {
    private val items: MutableList<Item> = mutableListOf()
    private val db: ItemDatabase = Room.databaseBuilder(
        ctx,
        ItemDatabase::class.java,
        "item.db"
    ).build()
    companion object {
        val mutex = Mutex()
    }

    override fun getCount(): Int {
        Log.d("ItemsAdapter", "SIZE: ${items.size}")
        return items.size
    }

    override fun getItem(idx: Int): Item {
        return items[idx]
    }

    override suspend fun getItems(): List<Item> {
        if (getCount() == 0) {
            clearAndFillItems()
        }
        return items
    }

    override suspend fun deleteItem(idx: Int) {
        delay(2000)
        val item = items[idx]
        db.itemDao().deleteItem(item)
        clearAndFillItems()
    }

    override suspend fun addItem(item: Item) {
        db.itemDao().addItem(item)
        clearAndFillItems()
    }

    private suspend fun clearAndFillItems() {
        mutex.withLock {
            items.clear()
            db.itemDao().getAllItems()?.let { items.addAll(it) }
        }
    }

    override suspend fun refreshList() {
        clearAndFillItems()
    }
}