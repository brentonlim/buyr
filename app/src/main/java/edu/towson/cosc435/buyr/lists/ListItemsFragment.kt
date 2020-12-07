package edu.towson.cosc435.buyr.lists

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IItemController
import kotlinx.android.synthetic.main.fragment_list_items.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListItemsFragment : Fragment() {
    private lateinit var itemController: IItemController

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        when(context) {
//            is IItemController -> itemController = context
//            else -> throw Exception("Context expected to implement IItemController")
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_items, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        addNewItemBtn.setOnClickListener {
//            itemController.launchAddNewItemScreen()
//        }
//
//        val adapter = ItemAdapter(itemController)
//        recyclerView_Item.adapter = adapter
//        recyclerView_Item.layoutManager = LinearLayoutManager(view.context)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        itemController.runAsync {
//            itemController.getItems()
//            withContext(Dispatchers.Main) {
//                recyclerView_Item?.adapter?.notifyDataSetChanged()
//            }
//        }
//    }
}