package edu.towson.cosc435.buyr.network

import android.util.Log
import com.google.gson.GsonBuilder
import edu.towson.cosc435.buyr.interfaces.IProductAPI
import edu.towson.cosc435.buyr.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*

class ProductAPI : IProductAPI {
    private val API_KEY = "34562e2bfe3e2430849da0a1bc25150c844831367614e35cd64350b5791ca840"

    override suspend fun fetchProductInfo(item: String, location: String): List<Item> {
        return withContext(Dispatchers.IO) {
            println("Fetching product data...")
            val URL = HttpUrl.Builder()
                .scheme("https")
                .host("serpapi.com")
                .addPathSegment("search")
                .addQueryParameter("q", item)
                .addQueryParameter("tbm", "shop")
                .addQueryParameter("location", location)
                .addQueryParameter("hl", "en")
                .addQueryParameter("gl", "us")
                .addQueryParameter("api_key", API_KEY)
                .build()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(URL)
                .get()
                .build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                Log.i(TAG, "Successfully retrieved product information")
                val productInfo = response?.body!!.string()
                val gson = GsonBuilder().create()
                Log.i(TAG, productInfo)
                val products = gson.fromJson<List<Item>>(productInfo, Item::class.java)
                products
            } else {
                Log.i(TAG, "Failed to retrieve product information")
                listOf()
            }
        }
    }

//    override suspend fun fetchThumbnail(imgUrl: String): Bitmap? {
//        val imageBytes = Base64.decode(imgUrl, 0)
//        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//    }

    companion object {
        const val TAG = "PRODUCT_API"
    }
}