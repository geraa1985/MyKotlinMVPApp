package com.geraa1985.mykotlinmvpapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.databinding.FragmentUserBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UserPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUserView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val USER_KEY = "user"

class UserFragment: MvpAppCompatFragment(), IUserView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }

    private lateinit var binding: FragmentUserBinding

    private val presenter by moxyPresenter {
        val user: GithubUser? = arguments?.getParcelable(USER_KEY)
        UserPresenter(user, MyApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init(user: GithubUser) {
        binding.userLogin.text = user.login
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}