package com.example.fragmentstest.interactors

import androidx.fragment.app.Fragment
import com.example.fragmentstest.interfaces.IAddUserUseCase
import com.example.fragmentstest.interfaces.IEditUserUseCase
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.models.User

class EditUserUseCase() : Fragment(), IEditUserUseCase {

    override fun editUser(myStorage: IStorage, position: Int, user: User) {
        myStorage.editUser(position, user)
    }

}
