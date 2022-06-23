package com.example.app.models

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil (
    private val oldList: MutableList<User>,
    private val newList: MutableList<User>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].number != newList[newItemPosition].number -> {
                false
            }
            oldList[oldItemPosition].photo != newList[newItemPosition].photo -> {
                false
            }
            oldList[oldItemPosition].isFavorite != newList[newItemPosition].isFavorite -> {
                false
            }
            else -> true
        }
    }
}