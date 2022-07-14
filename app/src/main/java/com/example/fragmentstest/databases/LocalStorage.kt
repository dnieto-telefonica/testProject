package com.example.fragmentstest.databases

import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.Storage

class LocalStorage : Storage {

    override fun getUsers(): List<User> = DataMemoryAbstraction.usersReference.toList()

    override fun editUser(position: Int, user: User) {
        DataMemoryAbstraction.usersReference[position] = user
    }

    override fun addUser(user: User) {
        DataMemoryAbstraction.usersReference.add(user)
        DataMemoryAbstraction.usersReference.sortBy { it.name }
    }

    override fun removeUser(position: Int) {
        DataMemoryAbstraction.usersReference.removeAt(position)
    }

}
