package com.example.fragmentstest.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.MyApplication
import com.example.fragmentstest.R
import com.example.fragmentstest.interactors.EditUserUseCase
import com.example.fragmentstest.interactors.RemoveUserUserCase
import com.example.fragmentstest.interfaces.Storage
import com.example.fragmentstest.models.User
import com.example.fragmentstest.presenters.FragmentDisplayPresenter
import com.example.fragmentstest.views.FragmentDisplayView
import com.example.fragmentstest.views.MainActivityView
import kotlinx.android.synthetic.main.fragment_display.*

class FragmentDisplay(
    var selectedUser: User,
    val position: Int
) : Fragment(), FragmentDisplayView {

    private val mainActivityView: MainActivityView by lazy { activity as MainActivity }

    private var isFavorite: Boolean = false
    private var isEdited: Boolean = false

    private val myStorage: Storage? by lazy {
        (this.context?.applicationContext as MyApplication).myDatabase
    }
    private val presenter: FragmentDisplayPresenter by lazy {
        FragmentDisplayPresenter(
            this, EditUserUseCase(myStorage),
            RemoveUserUserCase(myStorage), myStorage
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    override fun onResume() {
        super.onResume()

        ti_name.setText(selectedUser.name)
        ti_number.setText(selectedUser.number)
        ti_address.setText(selectedUser.address)
        app_bar_image.setImageResource(selectedUser.photo)
        isFavorite = selectedUser.isFavorite
        if (isFavorite)
            btn_fav.setText(R.string.leave_fav)
        else
            btn_fav.setText(R.string.add_fav)

        initializeEvents(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    fun initializeEvents(position: Int) {
        ti_name.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                this.setIsEditing(ti_name.text.toString(), selectedUser.name)
        }
        ti_number.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                this.setIsEditing(ti_number.text.toString(), selectedUser.number)
        }
        ti_address.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                this.setIsEditing(ti_address.text.toString(), selectedUser.address)
                ti_address.clearFocus()
            }
            false
        })
        btn_fav.setOnClickListener {
            isFavorite = !isFavorite
            this.setIsEditing(isFavorite, selectedUser.isFavorite)
            if (isFavorite)
                btn_fav.setText(R.string.leave_fav)
            else
                btn_fav.setText(R.string.add_fav)
        }
        fab.setOnClickListener {
            if (isEdited) {
                val newUser = User(
                    selectedUser.id,
                    ti_name.text.toString(),
                    ti_number.text.toString(),
                    ti_address.text.toString(),
                    selectedUser.photo,
                    isFavorite
                )
                selectedUser = newUser
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
            fab.backgroundTintList = this.resources.getColorStateList(R.color.green)
            fab.setImageResource(R.drawable.ic_edit)
            isEdited = true
        }
    }

    override fun onDeleteUser() {
        mainActivityView.onDeleteSearch()
    }

    override fun onEditUser() {
        isEdited = false
        mainActivityView.onEditUser()
        fab.backgroundTintList = this.resources.getColorStateList(R.color.red)
        fab.setImageResource(R.drawable.ic_delete)
    }

}
