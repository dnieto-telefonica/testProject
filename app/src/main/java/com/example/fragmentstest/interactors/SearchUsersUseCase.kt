package com.example.fragmentstest.interactors

import androidx.fragment.app.Fragment
import com.example.fragmentstest.interfaces.ISearchUsersUseCase
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.IStorage

class SearchUsersUseCase() : Fragment(), ISearchUsersUseCase {

    override fun getFilteredUsers(myStorage: IStorage, searchCondition: String): List<User> {
        return myStorage.getUsers().filter { it.name.toLowerCase().contains(searchCondition!!) }
    }

}
