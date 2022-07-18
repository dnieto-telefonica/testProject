package com.example.fragmentstest.views

import android.os.Bundle
import com.example.fragmentstest.models.User

interface FragmentListView {

    fun setupList()

    fun setupSearchInput()

    fun displayFoundContacts(users: List<User>)

    fun clearSearch()

}
