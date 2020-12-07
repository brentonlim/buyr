package edu.towson.cosc435.buyr.lists

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import either.Either
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.interfaces.IListController
import edu.towson.cosc435.buyr.model.List
import kotlinx.android.synthetic.main.activity_new_list.addListTitle
import kotlinx.android.synthetic.main.activity_new_list.inputListDesc
import kotlinx.android.synthetic.main.activity_new_list.inputListName
import kotlinx.android.synthetic.main.activity_new_list.saveListBtn
import kotlinx.android.synthetic.main.fragment_add_list.*
import java.util.*

class AddListFragment : Fragment() {
    private lateinit var listController: IListController
    private var editingListId: UUID? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is IListController -> listController = context
            else -> throw Exception("Context expected to implement IListController")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveListBtn.setOnClickListener {
            when(val list = getSongFromView()) {
                is Either.Left -> {
                    errorMsg.text = list.value
                    errorMsg.visibility = View.VISIBLE
                }
                is Either.Right -> {
                    listController.runAsync {
                        try {
                            listController.addNewList(list.value)
                            populateList()
                        } catch (e: Exception) {

                        }
                    }
                }
            }
        }
    }

    private fun getSongFromView(): Either<String, List> {
        val name = inputListName.editableText.toString()
        val desc = inputListDesc.editableText.toString()

        // make sure the user enters a list name
        if(name.isEmpty()) return Either.Left(resources.getString(R.string.addlist_error_msg_name))

        val id = if(editingListId == null) {
            UUID.randomUUID()
        } else {
            editingListId!!
        }
        return Either.Right(
            List(
                listID = id,
                listName = name,
                listDescription = desc,
                creationDate = Date()
            )
        )
    }

    fun populateList() {
        val list = listController.getListForEdit()
        inputListName.editableText.clear()
        inputListDesc.editableText.clear()
        if(list != null) {
            editingListId = list.listID
            inputListName.editableText.append(list.listName)
            inputListDesc.editableText.append(list.listDescription)
            saveListBtn.text = resources.getText(R.string.edit_list_btn_text)
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                addListTitle.text = resources.getText(R.string.edit_list_btn_text)
        } else {
            saveListBtn.text = resources.getText(R.string.save_new_list_btn)
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                addListTitle.text = resources.getText(R.string.new_list_title)
        }
    }
}