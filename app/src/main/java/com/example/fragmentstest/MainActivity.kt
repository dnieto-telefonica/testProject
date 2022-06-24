package com.example.fragmentstest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app.models.User
import com.example.fragmentstest.interfaces.IPassData

class MainActivity : AppCompatActivity(), IPassData {
    var fragmentDisplay: FragmentDisplay? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usersReference = (this.application as MyApplication).globalUsers
        initialize(usersReference)

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_list, FragmentList(this))
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_display, BlankFragment(supportFragmentManager,
                usersReference, this))
            .commit()
    }

    fun initialize(usersReference: MutableList<User>) {
        Log.d("LOG", "Generando usuarios...")
        usersReference.add(User("1","Daniel", "625793410",
            "Juan Gris 3", R.drawable.a, false))
        usersReference.add(User("2","María", "652352510",
            "Avenida de las Naciones", R.drawable.b, false))
        usersReference.add(User("3","Javier", "699243294",
            "Prueba", R.drawable.c, true))
        usersReference.add(User("4","Pepe", "625793410",
            "Juan Gris 3", R.drawable.a, false))
        usersReference.add(User("5","Almudena", "652352510",
            "Avenida de las Naciones", R.drawable.b, false))
        usersReference.add(User("6","Lucas", "699243294",
            "Prueba", R.drawable.c, false))
    }

    override fun onUpdateUser() {
        val usersReference = (this.application as MyApplication).globalUsers

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_display, BlankFragment(supportFragmentManager,
                usersReference, this))
            .commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_list, FragmentList(this))
            .commit()
    }

    override fun onSelectUser(bundle: Bundle) {
        fragmentDisplay = FragmentDisplay(this, bundle)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_display, fragmentDisplay!!)
            .commit()
    }

    override fun onCreateUser(user: User) {
        val usersReference = (this.application as MyApplication).globalUsers
        usersReference.add(user)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_list, FragmentList(this))
            .commit()
    }
}