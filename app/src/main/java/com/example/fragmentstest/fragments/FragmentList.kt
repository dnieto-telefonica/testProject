package com.example.fragmentstest.fragments

import android.annotation.SuppressLint
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
import com.example.fragmentstest.models.CustomAdapter
import com.example.fragmentstest.models.User
import com.example.fragmentstest.MyApplication
import com.example.fragmentstest.R
import com.example.fragmentstest.interactors.SearchUsersUseCase
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.views.IMainActivityView
import com.example.fragmentstest.presenters.FragmentListPresenter
import com.example.fragmentstest.views.IFragmentListView
import kotlinx.android.synthetic.main.fragment_list.*

class FragmentList(
    private val mainActivityView: IMainActivityView
) : Fragment(), IFragmentListView {
    var customAdapter = CustomAdapter(mainActivityView)

    private lateinit var myStorage: IStorage
    lateinit var presenter: FragmentListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myStorage = (this.context?.applicationContext as MyApplication).myDatabase
        presenter = FragmentListPresenter(this, SearchUsersUseCase(), myStorage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupList()
        this.setupSearchInput()
    }

    override fun setupList() {
        customAdapter.usersList = myStorage.getUsers().toList()

        rv_users.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = customAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setupSearchInput() {
        et_searchbar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("Before Text Changed: ", p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                et_searchbar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_remove, 0);
                presenter.performSearch(p0.toString().toLowerCase())
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("After Text Changed: ", s.toString())
            }
        })
        et_searchbar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= et_searchbar.right -
                    et_searchbar.compoundDrawables[2].bounds.width()) {
                    this.clearSearch()
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    // Dudo si esta función y la siguiente deberían delegar en el presentador
    override fun displayFoundContacts(users: List<User>) {
        customAdapter.usersList = users
        customAdapter.selectedRow = -1
        customAdapter.notifyDataSetChanged()
    }

    override fun clearSearch() {
        et_searchbar.setText("")
        et_searchbar.setCompoundDrawablesWithIntrinsicBounds(0, 0,
            R.drawable.ic_search, 0);
        customAdapter.usersList = myStorage.getUsers()
        mainActivityView.onDeleteSearch()
    }

    fun onDeleteUser() {
        customAdapter.selectedRow = -1
        customAdapter.usersList = myStorage.getUsers().toList()
    }

    fun onCreateUser() {
        customAdapter.usersList = myStorage.getUsers().toList()
    }

    fun onEditUser() {
        customAdapter.usersList = myStorage.getUsers().toList()
    }

}
