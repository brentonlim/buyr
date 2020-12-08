package edu.towson.cosc435.buyr.lists

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController
import kotlinx.android.synthetic.main.fragment_lists.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ListsFragment : Fragment() {
    private lateinit var listController: IListController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is IListController -> listController = context
            else -> throw Exception("Context expected to implement IListController")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewListBtn.setOnClickListener {
            listController.launchAddListScreen()
        }

        val adapter = ListAdapter(listController)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

//        val swipeDelete = object : SwipeDelete(this, 0, ItemTouchHelper.LEFT) {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val builder = AlertDialog.Builder(this@ListsFragment)
//                builder.setTitle("Delete Todo")
//                builder.setMessage("Are you sure you want to delete this item?")
//                builder.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
//                    adapter.deleteOnSwipe(viewHolder.adapterPosition)
//                }
//                builder.setNegativeButton("CANCEL", null)
//                builder.show()
//            }
//        }
//
//        val itemTouchHelper = ItemTouchHelper(swipeDelete)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//
//        val touchHelper = ItemTouchHelper(object :
//            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                val currentPosition = viewHolder.adapterPosition
//                val targetPosition = target.adapterPosition
//
//                Collections.swap(adapter.getTodos(), currentPosition, targetPosition)
//                adapter.notifyItemMoved(currentPosition, targetPosition)
//                adapter.notifyDataSetChanged()
//
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                TODO("Not yet implemented")
//            }
//        })
//
//        touchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onResume() {
        super.onResume()
        listController.runAsync {
            listController.getLists()
            withContext(Dispatchers.Main) {
                recyclerView?.adapter?.notifyDataSetChanged()
            }
        }
    }
}
