package com.geraa1985.mykotlinmvpapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.databinding.FragmentUsersBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UsersPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUsersView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import com.geraa1985.mykotlinmvpapp.ui.adapters.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment: MvpAppCompatFragment(), IUsersView, BackButtonListener {

    private lateinit var binding: FragmentUsersBinding

    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), MyApp.instance.router) }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
        val decor = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.rvUsers.addItemDecoration(decor)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}