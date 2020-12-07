package edu.towson.cosc435.buyr.lists

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController
import kotlinx.android.synthetic.main.fragment_lists.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListsFragment : Fragment() {
    private lateinit var listController: IListController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is IListController -> listController = context
            else -> throw Exception("Context expected to implement IListController")
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
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
