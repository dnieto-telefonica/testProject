package com.example.app.models

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentstest.R
import com.example.fragmentstest.interfaces.IPassData
import kotlin.properties.Delegates

class CustomAdapter(private val passData: IPassData): RecyclerView.Adapter<MyViewHolder>() {
    var selectedRow: Int = -1
    var usersList: List<User> by Delegates.observable(emptyList()) { _, old, new ->
        val diffUtil = MyDiffUtil(old, new)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        diffResults.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = usersList[position]
        if (position == selectedRow)
            holder.itemView.setBackgroundColor(Color.parseColor("#6200EE"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#BB86FC"));
        holder.onBindViewHolder(user = currentUser)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_main,
            parent, false)
        view.setOnClickListener {
            var bundle: Bundle = Bundle()
            Log.d("INFO", viewType.toString() + " " + usersList[viewType].toString())
            bundle.putString("userId", usersList[viewType].id)
            bundle.putString("userName", usersList[viewType].name)
            bundle.putString("userNumber", usersList[viewType].number)
            bundle.putInt("userPhoto", usersList[viewType].photo)
            bundle.putBoolean("userIsFavorite", usersList[viewType].isFavorite)
            bundle.putString("userAddress", usersList[viewType].address)
            bundle.putInt("position", viewType)
            passData.onSelectUser(bundle)
            selectedRow = viewType
            notifyDataSetChanged()
        }
        return MyViewHolder(
            view = view
        )
    }
}