package edu.towson.cosc435.buyr.watchlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController
import edu.towson.cosc435.buyr.interfaces.IWatchlistItemController
import edu.towson.cosc435.buyr.lists.ListAdapter
import kotlinx.android.synthetic.main.fragment_lists.*
import kotlinx.android.synthetic.main.fragment_lists.addNewListBtn
import kotlinx.android.synthetic.main.fragment_watchlist.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WatchlistFragment : Fragment() {
    private lateinit var watchlistItemController: IWatchlistItemController

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        when(context) {
//            is IWatchlistItemController -> watchlistItemController = context
//            else -> throw Exception("Context expected to implement IWatchlistItemController")
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewWatchlistItemBtn.setOnClickListener {
            watchlistItemController.launchAddNewWatchItemScreen()
        }

        val adapter = WLItemAdapter(watchlistItemController)
        recyclerView_WL.adapter = adapter
        recyclerView_WL.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onResume() {
        super.onResume()
        watchlistItemController.runAsync {
            watchlistItemController.getWatchItems()
            withContext(Dispatchers.Main) {
                recyclerView?.adapter?.notifyDataSetChanged()
            }
        }
    }*/
}