package com.example.fragmentstest.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.app.models.User
import com.example.fragmentstest.R
import com.example.fragmentstest.interfaces.IPassData
import kotlinx.android.synthetic.main.dialog_edit_text.*

class EditTextDialog(
    val users: List<User>,
    val passData: IPassData,
) : DialogFragment() {

    companion object {
        fun newInstance(users: List<User>, passData: IPassData): EditTextDialog {
            val dialog = EditTextDialog(users, passData)
            return dialog
        }
    }

    lateinit var etName: EditText
    lateinit var etNumber: EditText
    lateinit var etAddress: EditText
    var onCancel: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var view = requireActivity().layoutInflater.inflate(R.layout.dialog_edit_text, null)

        etName = view.findViewById(R.id.et_name)
        etNumber = view.findViewById(R.id.et_phone)
        etAddress = view.findViewById(R.id.et_address)

        val builder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton("CREAR") { _, _ ->
                val name = etName.text.toString()
                val number = etNumber.text.toString()
                val address = etAddress.text.toString()
                if (name != "" && number != "" && address != "") {
                    var user = User(users.size.toString(),
                        etName.text.toString(),
                        etNumber.text.toString(),
                        etAddress.text.toString(),
                        R.drawable.ic_launcher_background, false)
                    passData.onCreateUser(user)
                } else {
                    Toast.makeText(this.requireActivity().applicationContext,
                        "Error: Has dejado algún campo en blanco", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCELAR") { _, _ ->
                onCancel?.invoke()
            }
        val dialog = builder.create()

        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        return dialog
    }
}
