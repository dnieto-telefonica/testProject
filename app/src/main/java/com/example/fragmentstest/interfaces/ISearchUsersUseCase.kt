package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface ISearchUsersUseCase {

    fun getFilteredUsers(myStorage: IStorage, searchCondition: String): List<User>

}
