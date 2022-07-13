package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface IRemoveUserUseCase {

    fun removeUser(myStorage: IStorage, position: Int)

}
