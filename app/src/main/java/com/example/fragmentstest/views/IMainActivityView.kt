package com.example.fragmentstest.views

import android.os.Bundle

interface IMainActivityView {

    fun setupFragments()

    fun onSelectUser(bundle: Bundle)

    fun onCreateUser()

    fun onDeleteSearch()

    fun onEditUser()

}
