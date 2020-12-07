package edu.towson.cosc435.buyr.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IItemController
import edu.towson.cosc435.buyr.interfaces.IListController

class ItemAdapter(private val controller: IItemController): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_view, parent, false)
        val holder = ItemViewHolder(view)

        //Add deleteItemBtn listener
//        view.deleteItemBtn.setOnClickListener {
//            val position = holder.adapterPosition
//            controller.runAsync {
//                try {
//                    view.alpha = 0.5f
//                    controller.deleteItem(position)
//                    notifyItemRemoved(position)
//                } catch (e: Exception) {
//                    Toast.makeText(parent.context, "Failed to delete item", Toast.LENGTH_SHORT).show()
//                    view.alpha = 1.0f
//                }
//            }
//        }

        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = controller.getItem(position)
        holder.itemNameTv.text = item.itemName
        holder.storeNameOneTv.text = item.storeNameOne
        holder.storePriceOneTv.text = item.storePriceOne.toString()
        holder.storeNameTwoTv.text = item.storeNameTwo
        holder.storePriceTwoTv.text = item.storePriceTwo.toString()
    }

    override fun getItemCount(): Int {
        return controller.getItemsCount()
    }
}