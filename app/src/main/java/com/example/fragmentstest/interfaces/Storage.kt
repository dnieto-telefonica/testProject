package com.example.fragmentstest.interfaces

import com.example.fragmentstest.models.User

interface Storage {

    fun getUsers(): List<User>

    fun editUser(position: Int, user: User)

    fun addUser(user: User)

    fun removeUser(position: Int)

}
