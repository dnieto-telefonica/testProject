package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface EditUserUseCase {

    fun editUser(myStorage: Storage, position: Int, user: User)

}