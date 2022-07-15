package com.example.fragmentstest

import android.app.Application
import android.util.Log
import com.example.fragmentstest.databases.FileStorage
import com.example.fragmentstest.interfaces.Storage

class MyApplication : Application() {

    lateinit var myDatabase: Storage

    override fun onCreate() {
        super.onCreate()
        myDatabase = FileStorage() // Change this line to change the storage type
        Log.d("INFO", "Se ha iniciado la aplicación")
    }

}
