package com.example.fragmentstest.databases

import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.IStorage

class LocalStorage : IStorage {

    override fun getUsers(): MutableList<User> {
        return DataMemoryAbstraction.usersReference
    }

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
