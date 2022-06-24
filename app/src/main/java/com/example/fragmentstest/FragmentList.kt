package com.example.fragmentstest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.models.CustomAdapter
import com.example.app.models.User
import com.example.fragmentstest.interfaces.IPassData
import kotlinx.android.synthetic.main.fragment_list.*


class FragmentList(passDataP: IPassData) : Fragment() {
    var passData: IPassData = passDataP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        et_searchbar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("BTC", p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val usersReference = (requireActivity().applicationContext as MyApplication).globalUsers
                et_searchbar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_remove, 0);

                rv_users.apply {
                    layoutManager = LinearLayoutManager(activity)
                    val users = usersReference.filter { it.name.toLowerCase().contains(p0.toString().toLowerCase()!!) }
                    adapter = CustomAdapter(users as MutableList<User>, passData)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("ATC", s.toString())
            }
        })
        et_searchbar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= et_searchbar.getRight() - et_searchbar.getCompoundDrawables()
                        .get(2).getBounds().width()
                ) {
                    val usersReference = (this.requireActivity().applicationContext as MyApplication).globalUsers
                    rv_users.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = CustomAdapter(usersReference, passData)
                    }
                    et_searchbar.setText("")
                    et_searchbar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_search, 0);
                    passData.onUpdateUser()
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
        val usersReference = (this.requireActivity().applicationContext as MyApplication).globalUsers

        rv_users.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CustomAdapter(usersReference, passData)
        }
    }

}