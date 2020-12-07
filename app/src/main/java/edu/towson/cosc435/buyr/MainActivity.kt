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
import edu.towson.cosc435.buyr.interfaces.IItemController
import edu.towson.cosc435.buyr.interfaces.IItemsModel
import edu.towson.cosc435.buyr.model.Item
import edu.towson.cosc435.buyr.model.ItemsModel
import kotlinx.android.synthetic.main.fragment_list_items.*
import kotlinx.coroutines.*

//, IItemController
class MainActivity : AppCompatActivity(), IListController {
    private lateinit var listsModel: IListsModel
//    private lateinit var itemsModel: IItemsModel
    private var editingListIdx = -1
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        listsModel = ListsModel(ListDatabaseRepository(applicationContext))
//        itemsModel = ItemsModel(ListDatabaseRepository(applicationContext))

        scope.launch(Dispatchers.Default) {
            val existingLists = listsModel.getLists()
            existingLists.forEach { list ->
                if(existingLists.firstOrNull { l -> l.listID == list.listID } == null) {
                    listsModel.addList(list)
                }
                withContext(Dispatchers.Main) {
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override suspend fun deleteList(idx: Int) {
        scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    listsModel.deleteList(idx)
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity, "Failed to delete list",
                    Toast.LENGTH_SHORT
                ).show()
                throw e
            }
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
        scope.launch {
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
                Toast.makeText(
                    this@MainActivity, "Failed to add list",
                    Toast.LENGTH_SHORT
                ).show()
                throw e
            }
        }
    }

    override fun getListForEdit(): List? {
        if (editingListIdx < 0) return null
        return listsModel.editList(editingListIdx)
    }

    override fun launchListItemsScreen() {
        findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_listsFragment_to_listItemsFragment)
    }

    override fun runAsync(blk: suspend () -> Unit) {
        scope.launch { blk() }
    }

//    override suspend fun deleteItem(idx: Int) {
//        scope.launch {
//            try {
//                withContext(Dispatchers.IO) {
//                    itemsModel.deleteItem(idx)
//                }
//            } catch (e: Exception) {
//                Toast.makeText(
//                    this@MainActivity, "Failed to delete item",
//                    Toast.LENGTH_SHORT
//                ).show()
//                throw e
//            }
//        }
//    }
//
//    override fun getItem(idx: Int): Item {
//        return itemsModel.getItem(idx)
//    }
//
//    override suspend fun getItems(): kotlin.collections.List<Item> {
//        return itemsModel.getItems()
//    }
//
//    override fun getItemsCount(): Int {
//        return itemsModel.getItemsCount()
//    }
//
//    override suspend fun addNewItem(item: Item) {
//        scope.launch {
//            try {
//                withContext(Dispatchers.IO) {
//                    itemsModel.addItem(item)
//                }
//                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                    findNavController(R.id.nav_host_fragment).popBackStack()
//                } else {
//                    recyclerView_Item.adapter?.notifyDataSetChanged()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(
//                    this@MainActivity, "Failed to add item",
//                    Toast.LENGTH_SHORT
//                ).show()
//                throw e
//            }
//        }
//    }
//
//    override fun launchAddNewItemScreen() {
//        findNavController(R.id.nav_host_fragment)
//            .navigate(R.id.action_listItemsFragment_to_addItemFragment)
//    }

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