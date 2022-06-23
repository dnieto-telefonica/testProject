package com.example.fragmentstest

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentResultListener
import com.example.app.models.User
import com.example.fragmentstest.interfaces.IPassData
import kotlinx.android.synthetic.main.fragment_display.*
import kotlinx.android.synthetic.main.row_main.*

class FragmentDisplay(passDataP: IPassData, bundleP: Bundle) : Fragment() {
    var isFavorite: Boolean = false
    var user: User = User("0", "a", "b", "c", R.drawable.a, true)
    var isEditted: Boolean = false
    var isUserSelected: Boolean = false
    var passData: IPassData = passDataP
    val bundle: Bundle = bundleP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        var position = 0

        isUserSelected = true
        val usersReference =
            (this.requireActivity().applicationContext
                    as MyApplication).globalUsers

        user.name = bundle.getString("userName")!!
        user.number = bundle.getString("userNumber")!!
        user.address = bundle.getString("userAddress")!!
        user.id = bundle.getString("userId")!!
        user.photo = bundle.getInt("userPhoto")!!
        user.isFavorite = bundle.getBoolean("userIsFavorite")!!
        position = bundle.getInt("position")
        Log.d("DENTRO", user.name)
        ti_name.setText(user.name)
        ti_number.setText(user.number)
        ti_address.setText(user.address)
        app_bar_image.setImageResource(user.photo)
        isFavorite = user.isFavorite
        if (isFavorite)
            btn_fav.setText("Quitar de favoritos")
        else
            btn_fav.setText("Añadir a favoritos")

        initializeEvents(usersReference[position], usersReference, position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    fun initializeEvents(user: User, usersReference: MutableList<User>, position: Int) {
        ti_name.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val newName = ti_name.text.toString()
                if (newName != user.name) {
                    fab.backgroundTintList = this.getResources().getColorStateList(R.color.green)
                    fab.setImageResource(R.drawable.ic_edit)
                    isEditted = true
                }
              }
        })
        ti_number.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val newNumber = ti_number.text.toString()
                if (newNumber != user.number) {
                    fab.backgroundTintList = this.getResources().getColorStateList(R.color.green)
                    fab.setImageResource(R.drawable.ic_edit)
                    isEditted = true
                }
            }
        })
        ti_address.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val newAddress = ti_address.text.toString()
                if (newAddress != user.address) {
                    fab.backgroundTintList = this.getResources().getColorStateList(R.color.green)
                    fab.setImageResource(R.drawable.ic_edit)
                    isEditted = true
                }
            }
        })
        btn_fav.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite != user.isFavorite) {
                fab.backgroundTintList = this.getResources().getColorStateList(R.color.green)
                fab.setImageResource(R.drawable.ic_edit)
                isEditted = true
            }
            if (isFavorite)
                btn_fav.setText("Quitar de favoritos")
            else
                btn_fav.setText("Añadir a favoritos")
        }
        fab.setOnClickListener {
            if (isEditted) {
                Log.d("INFO", "Cambiando información del usuario...")
                val newUser: User = User(
                    user.id,
                    ti_name.text.toString(),
                    ti_number.text.toString(),
                    ti_address.text.toString(),
                    user.photo,
                    isFavorite
                )
                usersReference.set(position, newUser)

                isEditted = false
                fab.backgroundTintList = this.getResources().getColorStateList(R.color.red)
                fab.setImageResource(R.drawable.ic_delete)
                var bundle: Bundle = Bundle()
                passData.onUpdateUser()
            } else {

                AlertDialog.Builder(this.requireContext())
                    .setTitle("Eliminar contacto")
                    .setMessage("¿Estas seguro de querer eliminar este contacto?")
                    .setPositiveButton("SI",
                        DialogInterface.OnClickListener { dialog, which ->
                            usersReference.removeAt(position)
                            var bundle: Bundle = Bundle()
                            passData.onUpdateUser()
                        })
                    .setNegativeButton("NO", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }

    }

}