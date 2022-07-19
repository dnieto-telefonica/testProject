package com.example.fragmentstest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "usersList")
data class User(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "photo") var photo: Int,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean
) : Serializable
