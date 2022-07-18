package com.example.fragmentstest.databases

import android.util.Log
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.Storage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileStorage : Storage {

    lateinit var folder: File
    private val file: File by lazy {
        File(folder, "usersList.txt")
    }

    override fun getUsers(): MutableList<User> {
        var usersList: MutableList<User> = emptyList<User>().toMutableList()

        if (folder.exists() && file.exists()) {
            val fis = FileInputStream(file);
            val ois = ObjectInputStream(fis);
            var res = ois.readObject()
            usersList = (res as MutableList<User>)
            fis.close()
            ois.close()
        }

        return usersList
    }

    override fun editUser(position: Int, user: User) {
        var users = this.getUsers()
        val fos = FileOutputStream(file);
        val oos = ObjectOutputStream(fos);

        users[position] = user
        oos.writeObject(users);

        fos.close()
        oos.close();
    }

    override fun addUser(user: User) {
        var users = this.getUsers()
        val fos = FileOutputStream(file);
        val oos = ObjectOutputStream(fos);

        users.add(user)
        users.sortBy { it.name }
        oos.writeObject(users);

        fos.close()
        oos.close();
    }

    override fun removeUser(position: Int) {
        var users = this.getUsers()
        val fos = FileOutputStream(file);
        val oos = ObjectOutputStream(fos);

        users.removeAt(position)
        users.sortBy { it.name }
        oos.writeObject(users);

        fos.close()
        oos.close();
    }

    override fun initialize() {
        Log.d("INFO", "Inicializando Almacenamiento Externo")
    }

}
