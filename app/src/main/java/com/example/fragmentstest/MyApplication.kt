package com.example.fragmentstest

import android.app.Application
import android.util.Log
import com.example.fragmentstest.interfaces.Storage

class MyApplication : Application() {

    var myDatabase: Storage? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("INFO", "Se ha iniciado la aplicación")
    }

}
