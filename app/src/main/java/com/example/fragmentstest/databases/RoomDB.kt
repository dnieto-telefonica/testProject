package com.example.fragmentstest.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fragmentstest.interfaces.Storage
import com.example.fragmentstest.models.User

@Database(entities = [User::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): Storage

}