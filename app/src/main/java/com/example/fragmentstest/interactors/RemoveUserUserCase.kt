package com.example.fragmentstest.interactors

import androidx.fragment.app.Fragment
import com.example.fragmentstest.interfaces.IRemoveUserUseCase
import com.example.fragmentstest.interfaces.IStorage

class RemoveUserUserCase() : Fragment(), IRemoveUserUseCase {

    override fun removeUser(myStorage: IStorage, position: Int) {
        myStorage.removeUser(position)
    }

}
