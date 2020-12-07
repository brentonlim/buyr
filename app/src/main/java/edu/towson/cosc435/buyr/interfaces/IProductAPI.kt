package edu.towson.cosc435.buyr.interfaces

import edu.towson.cosc435.buyr.model.Item

interface IProductAPI {
    suspend fun fetchProductInfo(item: String, location: String): List<Item>
//    suspend fun fetchThumbnail(imgUrl: String): Bitmap?
}