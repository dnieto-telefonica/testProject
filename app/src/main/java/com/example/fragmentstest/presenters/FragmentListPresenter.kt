package com.example.fragmentstest.presenters

import android.util.Log
import com.example.fragmentstest.views.IFragmentListView
import com.example.fragmentstest.interactors.SearchUsersUseCase
import com.example.fragmentstest.interfaces.IStorage

class FragmentListPresenter(
    var listView: IFragmentListView?,
    private val searchUsersUseCase: SearchUsersUseCase,
    val myStorage: IStorage
) {

    fun performSearch(searchCondition: String) {
        Log.d("INFO", "Buscando la condición $searchCondition...")
        val users = searchUsersUseCase.getFilteredUsers(myStorage, searchCondition)
        listView?.displayFoundContacts(users)
    }

}
