package edu.towson.cosc435.buyr.model

import edu.towson.cosc435.buyr.interfaces.IListRepository
import edu.towson.cosc435.buyr.interfaces.IListsModel

class ListsModel(private val listRepository: IListRepository): IListsModel {
    private var editing = false
    private var editingIdx = -1

    override fun getListsCount(): Int {
        return listRepository.getCount()
    }

    override suspend fun getLists(): kotlin.collections.List<List> {
        return listRepository.getLists()
    }

    override fun getList(idx: Int): List {
        return listRepository.getList(idx)
    }

    override suspend fun deleteList(idx: Int) {
        listRepository.deleteList(idx)
    }

    override suspend fun addList(list: List) {
        if(editing) {
            listRepository.replaceList(editingIdx, list)
            clearEdit()
        } else {
            listRepository.addList(list)
        }
    }

    override fun editList(idx: Int): List {
        editingIdx = idx
        editing = true
        return listRepository.getList(idx)
    }

    override fun clearEdit() {
        editingIdx = -1
        editing = false
    }
}
