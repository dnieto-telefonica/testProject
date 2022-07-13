package com.example.fragmentstest.presenters

import android.util.Log
import com.example.fragmentstest.interactors.AddUserUseCase
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.views.IMainActivityView

class MainActivityPresenter(
    var displayView: IMainActivityView?,
    private val AddUserUseCase: AddUserUseCase,
    val myStorage: IStorage
) {

    fun addUser(user: User) {
        Log.d("INFO", "Añadiendo el usuario $user...")
        AddUserUseCase.addUser(myStorage, user)
        displayView?.onCreateUser()
    }

}
