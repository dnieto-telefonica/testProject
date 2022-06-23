package com.example.fragmentstest

import android.app.Application
import android.util.Log
import com.example.app.models.User

class MyApplication: Application() {
    var globalUsers: MutableList<User> = ArrayList()

    override fun onCreate() {
        super.onCreate()
        Log.d("INFO", "Se ha iniciado la aplicación")
    }
}