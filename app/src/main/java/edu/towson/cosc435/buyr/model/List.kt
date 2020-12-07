package edu.towson.cosc435.buyr.model

import java.util.*
import androidx.room.*
import kotlin.collections.List

@Entity(tableName = "list")

data class List (
    @PrimaryKey val listID: UUID,
    @ColumnInfo(name = "listName") val listName: String,
    @ColumnInfo(name = "listDescription") val listDescription: String,
    @ColumnInfo(name = "creationDate") val creationDate: Date
)
