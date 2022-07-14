package com.example.fragmentstest.interactors

import com.example.fragmentstest.interfaces.RemoveUserUseCase
import com.example.fragmentstest.interfaces.Storage

class RemoveUserUserCase() : RemoveUserUseCase {

    override fun removeUser(myStorage: Storage, position: Int) {
        myStorage.removeUser(position)
    }

}
