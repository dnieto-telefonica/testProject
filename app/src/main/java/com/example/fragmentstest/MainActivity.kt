package com.example.fragmentstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentstest.fragments.FragmentBlank
import com.example.fragmentstest.fragments.FragmentDisplay
import com.example.fragmentstest.fragments.FragmentList
import com.example.fragmentstest.interactors.AddUserUseCase
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.presenters.MainActivityPresenter
import com.example.fragmentstest.views.IMainActivityView

class MainActivity : AppCompatActivity(), IMainActivityView {
    private lateinit var myStorage: IStorage
    private lateinit var presenter: MainActivityPresenter

    var fragmentDisplay: FragmentDisplay? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myStorage = (this.application as MyApplication).myDatabase
        presenter = MainActivityPresenter(this, AddUserUseCase(), myStorage)
        this.setupFragments()
    }

    override fun onDeleteSearch() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_display, FragmentBlank(supportFragmentManager,
                presenter, myStorage)
            )
            .commit()
        (supportFragmentManager.findFragmentById(R.id.fl_fragment_list) as FragmentList).onDeleteUser()
    }

    override fun setupFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_list, FragmentList(this))
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_display,
                FragmentBlank(supportFragmentManager,
                    presenter, myStorage)
            )
            .commit()
    }

    override fun onSelectUser(bundle: Bundle) {
        fragmentDisplay = FragmentDisplay( this, bundle)
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

}
