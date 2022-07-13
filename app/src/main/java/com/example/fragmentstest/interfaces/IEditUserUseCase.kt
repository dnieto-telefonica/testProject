package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface IEditUserUseCase {

    fun editUser(myStorage: IStorage, position: Int, user: User)

}
