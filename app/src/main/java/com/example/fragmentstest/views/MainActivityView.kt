package com.example.fragmentstest.views

import android.os.Bundle
import com.example.fragmentstest.models.User

interface MainActivityView {

    fun setupFragments()

    fun onSelectUser(user: User, position: Int)

    fun onCreateUser()

    fun onDeleteSearch()

    fun onEditUser()

}
