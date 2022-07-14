package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface SearchUsersUseCase {

    fun getFilteredUsers(myStorage: Storage, searchCondition: String): List<User>

}
