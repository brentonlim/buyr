package edu.towson.cosc435.buyr.lists

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_view.view.*

class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val listNameTv: TextView = view.list_name
    val listDescriptionTv: TextView = view.list_desc
    val listDateTv: TextView = view.list_date
}