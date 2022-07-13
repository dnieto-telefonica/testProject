package com.example.fragmentstest.presenters

import android.util.Log
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.IEditUserUseCase
import com.example.fragmentstest.interfaces.IRemoveUserUseCase
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.views.IFragmentDisplayView

class FragmentDisplayPresenter(
    var displayView: IFragmentDisplayView?,
    private val editUserUseCase: IEditUserUseCase,
    private val removeUserUseCase: IRemoveUserUseCase,
    val myStorage: IStorage
) {

    fun editUser(user: User, position: Int) {
        Log.d("INFO", "Cambiando información del usuario...")
        editUserUseCase.editUser(myStorage, position, user)
        displayView?.onEditUser()
    }

    fun removeUser(position: Int) {
        Log.d("INFO", "Eliminando el usuario en la posición $position...")
        removeUserUseCase.removeUser(myStorage, position)
        displayView?.onDeleteUser()
    }

}
