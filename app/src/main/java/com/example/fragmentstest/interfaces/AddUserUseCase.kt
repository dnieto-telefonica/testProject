package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface AddUserUseCase {

    fun addUser(myStorage: Storage, user: User)
}
