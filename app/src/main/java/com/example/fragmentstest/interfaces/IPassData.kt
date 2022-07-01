package com.example.fragmentstest.interfaces

import android.os.Bundle
import com.example.app.models.User

interface IPassData {
    fun onSelectUser(bundle: Bundle)
    fun onCreateUser(user: User)
    fun onDeleteSearch()
}