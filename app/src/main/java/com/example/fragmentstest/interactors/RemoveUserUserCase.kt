package com.example.fragmentstest.interactors

import com.example.fragmentstest.interfaces.RemoveUserUseCase
import com.example.fragmentstest.interfaces.Storage

class RemoveUserUserCase(val myStorage: Storage) : RemoveUserUseCase {

    override fun removeUser(position: Int) {
        myStorage.removeUser(position)
    }

}
