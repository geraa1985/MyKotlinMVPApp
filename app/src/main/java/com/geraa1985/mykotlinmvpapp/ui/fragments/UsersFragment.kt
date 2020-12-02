package com.geraa1985.mykotlinmvpapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.databinding.FragmentUsersBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.api.ApiHolder
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UsersPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUsersView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import com.geraa1985.mykotlinmvpapp.ui.adapters.UserRVAdapter
import com.geraa1985.mykotlinmvpapp.ui.image.GlideImgLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, BackButtonListener {

    private lateinit var binding: FragmentUsersBinding

    private val presenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread(),
            GithubUsersRepo(ApiHolder.api),
            MyApp.instance.router
        )
    }

    private var adapter: UserRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initRvUsers() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserRVAdapter(presenter.usersListPresenter, GlideImgLoader())
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
}