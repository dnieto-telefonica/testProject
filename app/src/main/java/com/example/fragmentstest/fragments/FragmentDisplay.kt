package com.example.fragmentstest.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.fragmentstest.models.User
import com.example.fragmentstest.MyApplication
import com.example.fragmentstest.R
import com.example.fragmentstest.interactors.EditUserUseCase
import com.example.fragmentstest.interactors.RemoveUserUserCase
import com.example.fragmentstest.interfaces.IStorage
import com.example.fragmentstest.presenters.FragmentDisplayPresenter
import com.example.fragmentstest.views.IFragmentDisplayView
import com.example.fragmentstest.views.IMainActivityView
import kotlinx.android.synthetic.main.fragment_display.*

class FragmentDisplay(
    mainActivityViewP: IMainActivityView,
    bundleP: Bundle)
: Fragment(), IFragmentDisplayView {
    val bundle: Bundle = bundleP

    var isFavorite: Boolean = false
    private var isEditted: Boolean = false
    var isUserSelected: Boolean = false

    lateinit var myStorage: IStorage
    lateinit var presenter: FragmentDisplayPresenter
    var mainActivityView: IMainActivityView = mainActivityViewP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myStorage = (this.context?.applicationContext as MyApplication).myDatabase
        presenter = FragmentDisplayPresenter(this, EditUserUseCase(), RemoveUserUserCase(), myStorage)
    }

    override fun onResume() {
        super.onResume()

        isUserSelected = true
        var position: Int = bundle.getInt("position")
        ti_name.setText(bundle.getString("userName"))
        ti_number.setText(bundle.getString("userNumber"))
        ti_address.setText(bundle.getString("userAddress"))
        app_bar_image.setImageResource(bundle.getInt("userPhoto"))
        isFavorite = bundle.getBoolean("userIsFavorite")
        if (isFavorite)
            btn_fav.setText(R.string.leave_fav)
        else
            btn_fav.setText(R.string.add_fav)

        initializeEvents(myStorage.getUsers()[position], myStorage.getUsers(), position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    fun initializeEvents(user: User, usersReference: List<User>, position: Int) {
        ti_name.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus)
                this.setIsEditing(ti_name.text.toString(), user.name)
        })
        ti_number.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus)
                this.setIsEditing(ti_number.text.toString(), user.number)
        })
        ti_address.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus)
                this.setIsEditing(ti_address.text.toString(), user.address)
        })
        btn_fav.setOnClickListener {
            isFavorite = !isFavorite
            this.setIsEditing(isFavorite, user.isFavorite)
            if (isFavorite)
                btn_fav.setText(R.string.leave_fav)
            else
                btn_fav.setText(R.string.add_fav)
        }
        fab.setOnClickListener {
            if (isEditted) {
                val newUser: User = User(
                    user.id,
                    ti_name.text.toString(),
                    ti_number.text.toString(),
                    ti_address.text.toString(),
                    user.photo,
                    isFavorite
                )
                presenter.editUser(newUser, position)
            } else {
                AlertDialog.Builder(this.requireContext())
                    .setTitle(R.string.delete_contact)
                    .setMessage(R.string.delete_contact_conf)
                    .setPositiveButton(R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            presenter.removeUser(position)
                        })
                    .setNegativeButton(R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }
    }

    private fun setIsEditing(newData: Any, oldData: Any) {
        if (newData != oldData) {
            fab.backgroundTintList = this.getResources().getColorStateList(R.color.green)
            fab.setImageResource(R.drawable.ic_edit)
            isEditted = true
        }
    }

    override fun onDeleteUser() {
        mainActivityView.onDeleteSearch()
    }

    override fun onEditUser() {
        isEditted = false
        mainActivityView.onEditUser()
        fab.backgroundTintList = this.getResources().getColorStateList(R.color.red)
        fab.setImageResource(R.drawable.ic_delete)
    }

}
