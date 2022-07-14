package com.example.fragmentstest.interactors

import com.example.fragmentstest.interfaces.SearchUsersUseCase
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.Storage

class SearchUsersUseCase() :
    SearchUsersUseCase {

    override fun getFilteredUsers(myStorage: Storage, searchCondition: String): List<User> {
        return myStorage.getUsers().filter {
            it.name.toLowerCase().contains(searchCondition!!)
        }
    }

}
