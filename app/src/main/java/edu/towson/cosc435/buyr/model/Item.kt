package edu.towson.cosc435.buyr.model

import java.util.*
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item",
    foreignKeys = [
        ForeignKey(entity = List::class,
        parentColumns = arrayOf("listID"),
        childColumns = arrayOf("itemListID"),
        onDelete = CASCADE)
    ]
)

data class Item (
    @PrimaryKey val itemListID: UUID,
    @ColumnInfo(name = "itemName") @SerializedName("title") val itemName: String,
    @ColumnInfo(name = "itemLink") @SerializedName("link") val itemLink: String,
    @ColumnInfo(name = "storeName") @SerializedName("source") val storeName: String,
    @ColumnInfo(name = "price") @SerializedName("price") val price: Double
//    @ColumnInfo("thumbnail") @SerializedName("thumbnail") val thumbnail: String
)