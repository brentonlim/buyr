package edu.towson.cosc435.buyr.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListAdapter(private val controller: IListController): RecyclerView.Adapter<ListViewHolder>() {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_view, parent, false)
        val holder = ListViewHolder(view)

        //Add deleteListBtn listener
//        view.deleteListBtn.setOnClickListener {
//            val position = holder.adapterPosition
//            controller.runAsync {
//                try {
//                    view.alpha = 0.5f
//                    controller.deleteList(position)
//                    notifyItemRemoved(position)
//                } catch (e: Exception) {
//                    Toast.makeText(parent.context, "Failed to delete list", Toast.LENGTH_SHORT).show()
//                    view.alpha = 1.0f
//                }
//            }
//        }

        //Add editList Listener
//        view.editListBtn.setOnClickListener {
//            controller.editList(holder.adapterPosition)
//        }

        //Open list of items
        view.setOnClickListener {
            scope.launch {
                withContext(Dispatchers.Main) {
                    controller.launchListItemsScreen()
                }
            }
        }

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

    suspend fun deleteOnSwipe(position: Int) {
        controller.deleteList(position)
        notifyDataSetChanged()
    }
}
