package com.example.fragmentstest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app.models.CustomAdapter
import com.example.app.models.User
import com.example.fragmentstest.interfaces.IPassData

class MainActivity : AppCompatActivity(), IPassData {
    var fragmentDisplay: FragmentDisplay? = null
    var adapter = CustomAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_list, FragmentList(adapter,
                this))
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_display,
                BlankFragment(supportFragmentManager,
                    adapter, this))
            .commit()
    }

    fun initialize() {
        val mutable = adapter.usersList.toMutableList()
        val usersReference = (this.application as MyApplication).globalUsers

        Log.d("LOG", "Generando usuarios...")
        mutable.add(User("1","Almudena", "652352510",
            "Avenida de las Naciones", R.drawable.b, false))
        mutable.add(User("2","Daniel", "625793410",
            "Juan Gris 3", R.drawable.a, false))
        mutable.add(User("3","Javier", "699243294",
            "Prueba", R.drawable.c, true))
        mutable.add(User("4","Lucas", "699243294",
            "Prueba", R.drawable.c, false))
        mutable.add(User("5","María", "652352510",
            "Avenida de las Naciones", R.drawable.b, false))
        mutable.add(User("6","Pepe", "625793410",
            "Juan Gris 3", R.drawable.a, false))
        adapter.usersList = mutable.toList()
        // LISTA DE USUARIOS APP
        usersReference.add(User("1","Almudena", "652352510",
            "Avenida de las Naciones", R.drawable.b, false))
        usersReference.add(User("2","Daniel", "625793410",
            "Juan Gris 3", R.drawable.a, false))
        usersReference.add(User("3","Javier", "699243294",
            "Prueba", R.drawable.c, true))
        usersReference.add(User("4","Lucas", "699243294",
            "Prueba", R.drawable.c, false))
        usersReference.add(User("5","María", "652352510",
            "Avenida de las Naciones", R.drawable.b, false))
        usersReference.add(User("6","Pepe", "625793410",
            "Juan Gris 3", R.drawable.a, false))
    }

    override fun onDeleteSearch() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_display, BlankFragment(supportFragmentManager,
                adapter, this))
            .commit()
        adapter.selectedRow = -1
        adapter.notifyDataSetChanged()
    }

    override fun onSelectUser(bundle: Bundle) {
        fragmentDisplay = FragmentDisplay(adapter, this, bundle)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_display, fragmentDisplay!!)
            .commit()
    }

    override fun onCreateUser(user: User) {
        val mutable = adapter.usersList.toMutableList()
        mutable.add(user)
        mutable.sortBy { it.name }
        adapter.usersList = mutable.toList()
        val usersReference = (this.application as MyApplication).globalUsers
        usersReference.add(user)
        usersReference.sortBy { it.name }
    }
}