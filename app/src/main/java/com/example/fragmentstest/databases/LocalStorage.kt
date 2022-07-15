package com.example.fragmentstest.databases

import android.util.Log
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.R
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

    override fun initialize(activity: MainActivity) {
        Log.d("INFO", "Inicializando Almacenamiento en Memoria")
        DataMemoryAbstraction.usersReference.add(
            User(
                "1", "Almudena", "652352510",
                "Calle de Prueba 1", R.drawable.b, false
            )
        )
        DataMemoryAbstraction.usersReference.add(
            User(
                "2", "Daniel", "625793410",
                "Calle de Prueba 2", R.drawable.a, false
            )
        )
        DataMemoryAbstraction.usersReference.add(
            User(
                "3", "Javier", "699243294",
                "Calle de Prueba 3", R.drawable.c, true
            )
        )
        DataMemoryAbstraction.usersReference.add(
            User(
                "4", "Lucas", "699243294",
                "Calle de Prueba 4", R.drawable.c, false
            )
        )
        DataMemoryAbstraction.usersReference.add(
            User(
                "5", "María", "652352510",
                "Calle de Prueba 5", R.drawable.b, false
            )
        )
        DataMemoryAbstraction.usersReference.add(
            User(
                "6", "Pepe", "654634534",
                "Calle de Prueba 6", R.drawable.a, false
            )
        )
    }

}
