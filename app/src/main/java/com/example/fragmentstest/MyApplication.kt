package com.example.fragmentstest

import android.app.Application
import android.util.Log
import com.example.fragmentstest.databases.LocalStorage

class MyApplication: Application() {
    var myDatabase = LocalStorage()

    override fun onCreate() {
        super.onCreate()
        Log.d("INFO", "Se ha iniciado la aplicación")
    }

}
