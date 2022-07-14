package com.example.fragmentstest.interactors

import com.example.fragmentstest.interfaces.EditUserUseCase
import com.example.fragmentstest.interfaces.Storage
import com.example.fragmentstest.models.User

class EditUserUseCase() : EditUserUseCase {

    override fun editUser(myStorage: Storage, position: Int, user: User) {
        myStorage.editUser(position, user)
    }

}
