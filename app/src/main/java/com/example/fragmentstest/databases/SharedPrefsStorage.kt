package com.example.fragmentstest.databases

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.R
import com.example.fragmentstest.models.User
import com.example.fragmentstest.interfaces.Storage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsStorage : Storage {

    private val type = object : TypeToken<MutableList<User>?>() {}.type
    private lateinit var sharedPrefEditor: SharedPreferences.Editor
    private lateinit var sharedPref: SharedPreferences
    private val gson by lazy { Gson() }

    override fun getUsers(): MutableList<User> {
        val contacts: String? = sharedPref.getString("users", "")
        return if (contacts != "")
            gson.fromJson(contacts, type)
        else
            emptyList<User>().toMutableList()
    }

    override fun editUser(position: Int, user: User) {
        val usersList = getUsers()
        usersList[position] = user
        saveList(usersList)
    }

    override fun addUser(user: User) {
        val usersList = getUsers()
        usersList.add(usersList.size, user)
        saveList(usersList)
    }

    override fun removeUser(position: Int) {
        val usersList = getUsers()
        usersList.removeAt(position)
        usersList.sortBy { it.name }
        saveList(usersList)
    }

    override fun initialize(activity: MainActivity) {
        Log.d("INFO", "Inicializando Almacenamiento Externo")
        sharedPref = activity.getSharedPreferences(
            activity.getString(R.string.error_creating_user), Context.MODE_PRIVATE
        )
        sharedPrefEditor = sharedPref.edit()
    }

    private fun saveList(usersList: List<User>) {
        val usersInJson = gson.toJson(usersList)
        sharedPrefEditor.putString("users", usersInJson)
        sharedPrefEditor.commit();
    }

}
