package com.example.fragmentstest.presenters

import android.util.Log
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.EditUserUseCase
import com.example.fragmentstest.interfaces.RemoveUserUseCase
import com.example.fragmentstest.interfaces.Storage
import com.example.fragmentstest.views.FragmentDisplayView

class FragmentDisplayPresenter(
    var displayView: FragmentDisplayView?,
    private val editUserUseCase: EditUserUseCase,
    private val removeUserUseCase: RemoveUserUseCase,
    val myStorage: Storage?
) {

    fun editUser(user: User, position: Int) {
        Log.d("INFO", "Cambiando información del usuario...")
        editUserUseCase.editUser(position, user)
        displayView?.onEditUser()
    }

    fun removeUser(position: Int) {
        Log.d("INFO", "Eliminando el usuario en la posición $position...")
        removeUserUseCase.removeUser(position)
        displayView?.onDeleteUser()
    }

}
