package com.geraa1985.mykotlinmvpapp.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.mykotlinmvpapp.databinding.ActivityMainBinding
import com.geraa1985.mykotlinmvpapp.model.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.presenter.Presenter
import com.geraa1985.mykotlinmvpapp.view.View
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), View {

    private lateinit var binding : ActivityMainBinding

    private val presenter by moxyPresenter { Presenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}