package edu.towson.cosc435.buyr.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.cosc435.buyr.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lists.*


class ListsFragment : Fragment() {

    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //INIT RECYCLER VIEW
        //VIEW ID OF RECYCLERVIEW
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            listAdapter = ListAdapter()
            adapter = listAdapter
        }

        //ADDING DATA TO LIST
        val data = ListItemHolder.createDataSet()
        listAdapter.showListView(data)
    }
}