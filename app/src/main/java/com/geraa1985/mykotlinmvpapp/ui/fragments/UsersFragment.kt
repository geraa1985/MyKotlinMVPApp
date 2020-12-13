package com.geraa1985.mykotlinmvpapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.R
import com.geraa1985.mykotlinmvpapp.databinding.FragmentUsersBinding
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UsersPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUsersView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import com.geraa1985.mykotlinmvpapp.ui.adapters.UserRVAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, BackButtonListener {

    private lateinit var binding: FragmentUsersBinding

    @InjectPresenter
    lateinit var presenter: UsersPresenter

    private var adapter: UserRVAdapter? = null
    private lateinit var searchItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        MyApp.instance.appGraph.inject(this)

        val mainToolbar = binding.mainToolbar
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(mainToolbar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Enter login"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchItem.collapseActionView()
                presenter.searchUser(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchUsers(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initRvUsers() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    @SuppressLint("ShowToast")
    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun onStop() {
        searchItem.collapseActionView()
        super.onStop()
    }
}