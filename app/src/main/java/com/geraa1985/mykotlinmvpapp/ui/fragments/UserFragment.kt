package com.geraa1985.mykotlinmvpapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.databinding.FragmentUserBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.api.ApiHolder
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UserPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IUserView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import com.geraa1985.mykotlinmvpapp.ui.adapters.RepoRVAdapter
import com.geraa1985.mykotlinmvpapp.ui.image.GlideImgLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment: MvpAppCompatFragment(), IUserView, BackButtonListener {

    companion object {
        private const val USER_KEY = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }

    private lateinit var binding: FragmentUserBinding
    private var adapter: RepoRVAdapter? = null

    private val presenter by moxyPresenter {
        val user: GithubUser? = arguments?.getParcelable(USER_KEY)
        UserPresenter(user, AndroidSchedulers.mainThread(), GithubUsersRepo(ApiHolder.api), MyApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun showLogin(login: String) {
        binding.userLogin.text = login
    }

    override fun showAvatar(url: String) {
        GlideImgLoader().loadInto(url, binding.avatar)
    }

    override fun initRvRepos() {
        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        adapter = RepoRVAdapter(presenter.reposListPresenter)
        binding.rvRepos.adapter = adapter
    }

    override fun updateReposList() {
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