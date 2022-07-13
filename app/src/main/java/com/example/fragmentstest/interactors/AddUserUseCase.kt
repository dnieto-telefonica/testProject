package com.example.fragmentstest.interactors

import androidx.fragment.app.Fragment
import com.example.fragmentstest.interfaces.IAddUserUseCase
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.models.User

class AddUserUseCase() : Fragment(), IAddUserUseCase {

    override fun addUser(myStorage: IStorage, user: User) {
        myStorage.addUser(user)
    }
}
