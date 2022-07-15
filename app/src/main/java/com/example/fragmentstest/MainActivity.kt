package com.example.fragmentstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentstest.fragments.FragmentBlank
import com.example.fragmentstest.fragments.FragmentDisplay
import com.example.fragmentstest.fragments.FragmentList
import com.example.fragmentstest.interactors.AddUserUseCase
import com.example.fragmentstest.interfaces.Storage
import com.example.fragmentstest.models.User
import com.example.fragmentstest.presenters.MainActivityPresenter
import com.example.fragmentstest.views.MainActivityView

class MainActivity : AppCompatActivity(), MainActivityView {

    var fragmentDisplay: FragmentDisplay? = null
    private val presenter: MainActivityPresenter by lazy {
        MainActivityPresenter(
            this,
            AddUserUseCase(),
            myStorage
        )
    }
    private val myStorage: Storage by lazy {
        (this.application as MyApplication).myDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myStorage.initialize(this)
        setContentView(R.layout.activity_main)
        setupFragments()
    }

    override fun onDeleteSearch() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fl_fragment_display, FragmentBlank(myStorage)
            )
            .commit()
        (supportFragmentManager.findFragmentById(R.id.fl_fragment_list) as FragmentList).onDeleteUser()
    }

    override fun setupFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_list, FragmentList())
            .commit()
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fl_fragment_display,
                FragmentBlank(myStorage)
            )
            .commit()
    }

    override fun onSelectUser(user: User, position: Int) {
        fragmentDisplay = FragmentDisplay(user, position)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_display, fragmentDisplay!!)
            .commit()
    }

    override fun onCreateUser() {
        (supportFragmentManager.findFragmentById(R.id.fl_fragment_list) as FragmentList).onCreateUser()
    }

    override fun onEditUser() {
        (supportFragmentManager.findFragmentById(R.id.fl_fragment_list) as FragmentList).onEditUser()
    }

    fun addUser(user: User) {
        presenter.addUser(user)
    }

}

