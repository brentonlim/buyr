package edu.towson.cosc435.buyr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.towson.cosc435.buyr.model.List
import kotlinx.android.synthetic.main.activity_new_list.*

class NewListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)
    }

    private fun populateList(list: List?) {
        if(list == null) return
        inputListName.editableText.append(list.listName)
        inputListDesc.editableText.append(list.listDescription)
        saveListBtn.text = resources.getString(R.string.edit_list_btn_text)
    }

    companion object {
        const val LIST_KEY = "list_key"
    }
}