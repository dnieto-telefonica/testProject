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

class CustomAdapter(private val users: MutableList<User>,
                    private val passData: IPassData
): RecyclerView.Adapter<MyViewHolder>() {
    private var oldPersonList: MutableList<User> = ArrayList()
    private var selectedRow: Int = -1
    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = users[position]
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
        var bundle: Bundle = Bundle()
        bundle.putString("userId", users[viewType].id)
        bundle.putString("userName", users[viewType].name)
        bundle.putString("userNumber", users[viewType].number)
        bundle.putInt("userPhoto", users[viewType].photo)
        bundle.putBoolean("userIsFavorite", users[viewType].isFavorite)
        bundle.putString("userAddress", users[viewType].address)
        bundle.putInt("position", viewType)
        view.setOnClickListener {
            passData.onSelectUser(bundle)
            selectedRow = viewType
            notifyDataSetChanged()
        }
        return MyViewHolder(
            view = view
        )
    }

    fun setData(newPersonList: MutableList<User>) {
        val diffUtil = MyDiffUtil(oldPersonList, newPersonList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        diffResults.dispatchUpdatesTo(this)
    }
}