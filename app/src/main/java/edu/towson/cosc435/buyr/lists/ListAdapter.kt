package edu.towson.cosc435.buyr.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.buyr.R
import kotlinx.android.synthetic.main.list_item_view.view.*

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<ListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> {
                //BINDING the data to the recyclerview
                holder.bind(list.get(position))
            }
        }
    }

    fun showListView(listView: List<ListItem>){
        list = listView
    }

    //view Holder class to DISPLAY OUR DATA
    class ListViewHolder constructor(listView: View) : RecyclerView.ViewHolder(listView) {
        //here, link the data to the list_item_view//
        val listTitle = listView.list_title
        val listDesc = listView.list_desc
        val listDate = listView.list_date

        //here, binding the above vals to the view holder
        //taking the LIST MODEL PARAMS
        fun bind(listItem: ListItem) {
            listTitle.text = listItem.title
            listDesc.text = listItem.desc
            listDate.text = listItem.dateCreated
        }

    }

}