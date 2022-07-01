
package com.example.fragmentstest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.app.models.CustomAdapter
import com.example.app.models.User
import com.example.fragmentstest.dialogs.EditTextDialog
import com.example.fragmentstest.interfaces.IPassData
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment(val supportFragmentManager: FragmentManager,
                    val adapter: CustomAdapter,
                    passDataP: IPassData) : Fragment() {
    var passData: IPassData = passDataP

    override fun onResume() {
        super.onResume()
        fab_createUser.setOnClickListener {
            val dialog = EditTextDialog.newInstance(adapter.usersList, passData)
            dialog.show(supportFragmentManager, "editDescription")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }
}