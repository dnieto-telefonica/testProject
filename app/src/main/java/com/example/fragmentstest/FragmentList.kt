package com.example.fragmentstest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.models.CustomAdapter
import com.example.fragmentstest.interfaces.IPassData
import kotlinx.android.synthetic.main.fragment_list.*

class FragmentList(passDataP: IPassData) : Fragment() {
    var passData: IPassData = passDataP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragmentManager.setFragmentResultListener("userUpdated", this)
        { requestKey, bundle ->
            val usersReference = (this.requireActivity().applicationContext as MyApplication).globalUsers

            rv_users.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = CustomAdapter(usersReference, passData)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usersReference = (this.requireActivity().applicationContext as MyApplication).globalUsers

        rv_users.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CustomAdapter(usersReference, passData)
        }
    }

}