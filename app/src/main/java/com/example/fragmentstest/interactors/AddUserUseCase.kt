package com.example.fragmentstest.interactors

import com.example.fragmentstest.interfaces.AddUserUseCase
import com.example.fragmentstest.interfaces.Storage
import com.example.fragmentstest.models.User

class AddUserUseCase() : AddUserUseCase {

    override fun addUser(myStorage: Storage, user: User) {
        myStorage.addUser(user)
    }

}
