package edu.towson.cosc435.buyr.lists

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val itemNameTv: TextView = view.item_name
//    val itemThumbnail: ImageView = view.item_thumbnail
    val storeNameOneTv: TextView = view.store_name_1
    val storePriceOneTv: TextView = view.store_price_1
    val storeNameTwoTv: TextView = view.store_name_2
    val storePriceTwoTv: TextView = view.store_price_2
}