package com.example.fragmentstest.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fragmentstest.models.User

@Dao
interface Storage {

    @Query("SELECT * FROM usersList")
    fun getUsers(): List<User>

    @Update
    fun editUser(user: User)

    @Insert
    fun addUser(user: User)

    @Delete
    fun removeUser(user: User)

}
