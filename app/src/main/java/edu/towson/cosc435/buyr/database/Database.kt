package edu.towson.cosc435.buyr.database

import edu.towson.cosc435.buyr.model.List
import edu.towson.cosc435.buyr.model.Item
import androidx.room.*
import java.util.*

@Dao
interface ListDao {
    @Insert
    suspend fun addList(list: List)

    @Update
    suspend fun updateList(list: List)

    @Delete
    suspend fun deleteList(list: List)

    @Query("SELECT listID, listName, listDescription, creationDate FROM list")
    suspend fun getAllLists(): kotlin.collections.List<List>?
}

@Dao
interface ItemDao {
    @Insert suspend fun addItem(item: Item)

    @Delete suspend fun deleteItem(item: Item)

    @Query("SELECT itemListID, itemName, itemLink, storeNameOne, storePriceOne, storeNameTwo, storePriceTwo FROM item")
    suspend fun getAllItems(): kotlin.collections.List<Item>?
}

class UUIDConvert {
    @TypeConverter
    fun fromString(uuid: String): UUID {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun toString(uuid: UUID): String {
        return uuid.toString()
    }
}

class DateConvert {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

@Database(entities = [Item::class, List::class], version = 1, exportSchema = false)

@TypeConverters(UUIDConvert::class, DateConvert::class)
abstract class ListDatabase : RoomDatabase() {
    abstract fun listDao(): ListDao
}

@TypeConverters(UUIDConvert::class)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}

