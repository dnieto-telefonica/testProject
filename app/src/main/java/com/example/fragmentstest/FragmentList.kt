package com.example.fragmentstest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.models.CustomAdapter
import com.example.fragmentstest.interfaces.IPassData
import kotlinx.android.synthetic.main.fragment_list.*


class FragmentList(val customAdapter: CustomAdapter, passDataP: IPassData) : Fragment() {
    var passData: IPassData = passDataP

    override fun onResume() {
        super.onResume()
        et_searchbar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("BTC", p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                et_searchbar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_remove, 0);
                val usersReference = (requireActivity().applicationContext as MyApplication).globalUsers
                val users = usersReference.filter { it.name.toLowerCase().contains(p0.toString().toLowerCase()!!) }
                customAdapter.usersList = users
                customAdapter.selectedRow = -1
                customAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("ATC", s.toString())
            }
        })
        rv_users.setItemAnimator(object : DefaultItemAnimator() {
            override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {
                holder.itemView.clearAnimation()
                holder.itemView.animate()
                    .alpha(0f)
                    .setInterpolator(AccelerateInterpolator(2f))
                    .setDuration(1350)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            dispatchRemoveFinished(holder)
                        }
                    })
                    .start()
                //
                return false
            }
        })

        et_searchbar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= et_searchbar.getRight() - et_searchbar.getCompoundDrawables()
                        .get(2).getBounds().width()
                ) {
                    et_searchbar.setText("")
                    et_searchbar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_search, 0);
                    val usersReference = (requireActivity().applicationContext as MyApplication).globalUsers
                    customAdapter.usersList = usersReference.toList()
                    passData.onDeleteSearch()
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_users.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = customAdapter
        }
    }

}