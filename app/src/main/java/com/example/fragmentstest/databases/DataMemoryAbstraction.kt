package com.example.fragmentstest.databases

import com.example.fragmentstest.models.User

object DataMemoryAbstraction {
    var usersReference: MutableList<User> = emptyList<User>().toMutableList()
}
