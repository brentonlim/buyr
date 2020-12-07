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
    @ColumnInfo(name = "itemName") val itemName: String,
    @ColumnInfo(name = "itemLink") val itemLink: String,
    @ColumnInfo(name = "storeNameOne") val storeNameOne: String,
    @ColumnInfo(name = "storePriceOne") val storePriceOne: Double,
    @ColumnInfo(name = "storeNameTwo")  val storeNameTwo: String,
    @ColumnInfo(name = "storePriceTwo") val storePriceTwo: Double
//    @ColumnInfo("thumbnail") @SerializedName("thumbnail") val thumbnail: String
)