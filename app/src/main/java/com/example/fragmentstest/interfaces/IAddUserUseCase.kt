package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface IAddUserUseCase {

    fun addUser(myStorage: IStorage, user: User)
}
