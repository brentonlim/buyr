package edu.towson.cosc435.buyr.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController

class ListAdapter(private val controller: IListController): RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_view, parent, false)
        val holder = ListViewHolder(view)

        //Add deleteListBtn listener
        //Add editList Listener

        return holder
    }

    override fun getItemCount(): Int {
        return controller.getListsCount()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = controller.getList(position)
        holder.listNameTv.text = list.listName
        holder.listDescriptionTv.text = list.listDescription
        holder.listDateTv.text = list.creationDate.toString()
    }
}
