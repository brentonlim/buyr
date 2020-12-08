package edu.towson.cosc435.buyr.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IWatchlistItemController
import edu.towson.cosc435.buyr.lists.ItemViewHolder

class WLItemAdapter(private val controller: IWatchlistItemController): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        val holder = ItemViewHolder(view)

        //Add deleteWLItemBtn listener
//        view.deleteWLItemBtn.setOnClickListener {
//            val position = holder.adapterPosition
//            controller.runAsync {
//                try {
//                    view.alpha = 0.5f
//                    controller.deleteWatchItem(position)
//                    notifyItemRemoved(position)
//                } catch (e: Exception) {
//                    Toast.makeText(parent.context, "Failed to delete watchlist item", Toast.LENGTH_SHORT).show()
//                    view.alpha = 1.0f
//                }
//            }
//        }

        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = controller.getWatchItem(position)
        holder.itemNameTv.text = item.itemName
        holder.storeNameOneTv.text = item.storeNameOne
        holder.storePriceOneTv.text = item.storePriceOne.toString()
        holder.storeNameTwoTv.text = item.storeNameTwo
        holder.storePriceTwoTv.text = item.storePriceTwo.toString()
    }

    override fun getItemCount(): Int {
        return controller.getWatchItemsCount()
    }

}