package edu.towson.cosc435.buyr.lists

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController
import edu.towson.cosc435.buyr.model.Item
import either.Either
import kotlinx.android.synthetic.main.activity_new_list.*
import java.util.*

class AddItemFragment : Fragment() {
    private lateinit var listController: IListController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is IListController -> listController = context
            else -> throw Exception("Context expected to implement IListController")
        }
    }

    private fun getItemFromView(): Either<String, Item> {
        val name = inputListName.editableText.toString()

        // make sure the user enters an item name
        if(name.isEmpty()) return Either.Left(resources.getString(R.string.additem_error_msg_name))

        return Either.Right(
            Item(
                itemListID = UUID.randomUUID(),
                itemName = name,
                itemLink = "",
                storeNameOne = "he",
                storePriceOne = 0.0,
                storeNameTwo = "he",
                storePriceTwo = 0.0
            )
        )
    }
}