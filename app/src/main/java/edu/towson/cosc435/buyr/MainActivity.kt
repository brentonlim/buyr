package edu.towson.cosc435.buyr

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.towson.cosc435.buyr.database.ListDatabaseRepository
import edu.towson.cosc435.buyr.interfaces.IListController
import edu.towson.cosc435.buyr.interfaces.IListsModel
import edu.towson.cosc435.buyr.model.List
import edu.towson.cosc435.buyr.model.ListsModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lists.*
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), IListController {
    private lateinit var listsModel: IListsModel
    private var editingListIdx = -1
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        listsModel = ListsModel(ListDatabaseRepository(applicationContext))
    }

    override suspend fun deleteList(idx: Int) {
        try {
            withContext(Dispatchers.IO) {
                listsModel.deleteList(idx)
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Failed to delete list",
                Toast.LENGTH_SHORT).show()
            throw e
        }
    }

    override fun launchAddListScreen() {
        editingListIdx = -1
        listsModel.clearEdit()
        findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_listsFragment_to_addListFragment)
    }

    override fun getList(idx: Int): List {
        return listsModel.getList(idx)
    }

    override suspend fun getLists(): kotlin.collections.List<List> {
        return listsModel.getLists()
    }

    override fun getListsCount(): Int {
        return listsModel.getListsCount()
    }

    override fun editList(idx: Int) {
        editingListIdx = idx
        findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_listsFragment_to_addListFragment)
    }

    override suspend fun addNewList(list: List) {
        try {
            withContext(Dispatchers.IO) {
                listsModel.addList(list)
            }
            editingListIdx = -1
            listsModel.clearEdit()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                findNavController(R.id.nav_host_fragment).popBackStack()
            } else {
                recyclerView.adapter?.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Failed to add list",
                Toast.LENGTH_SHORT).show()
            throw e
        }
    }

    override fun getListForEdit(): List? {
        if (editingListIdx < 0) return null
        return listsModel.editList(editingListIdx)
    }

    override fun runAsync(blk: suspend () -> Unit) {
        scope.launch { blk() }
    }

    override fun onStop() {
        super.onStop()
        scope.cancel()
    }

//    private val sessionManagement = SessionManager(this)
//
//    private fun logout() {
//        //remove session and return to login View
//        //reset session to '-1'
//        //go back to login screen>?
//        sessionManagement.removeSession()
//
//        //back to loginRegister? create if else to confirm with the user?
//        toLoginScreen()
//
//    }
//
//    private fun toLoginScreen() {
//        //from 'this' to whichever activity you want
//        val intent = Intent(this, LoginRegister::class.java)
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//        startActivity(intent)
//    }
}