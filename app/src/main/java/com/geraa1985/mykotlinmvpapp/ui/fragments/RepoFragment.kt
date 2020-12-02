package com.geraa1985.mykotlinmvpapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.databinding.FragmentRepoBinding
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.presenter.RepoPresenter
import com.geraa1985.mykotlinmvpapp.mvp.view.IRepoView
import com.geraa1985.mykotlinmvpapp.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), IRepoView, BackButtonListener {

    companion object {
        private const val REPO_KEY = "repo"

        fun newInstance(repo: UserRepo) = RepoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_KEY, repo)
            }
        }
    }

    private lateinit var binding: FragmentRepoBinding

    private val presenter by moxyPresenter {
        val repo: UserRepo? = arguments?.getParcelable(REPO_KEY)
        RepoPresenter(repo, MyApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun showName(name: String) {
        binding.repoName.text = name
    }

    override fun showLang(lang: String) {
        binding.repoLang.text = lang
    }

    override fun showCreated(createDate: String) {
        binding.repoCreated.text = createDate.substring(0, 10)
    }

    override fun showUpdated(updateDate: String) {
        binding.repoUpdated.text = updateDate.substring(0, 10)
    }

    override fun showForks(forks: String) {
        binding.repoForks.text = forks
    }

    override fun showWatchers(watchers: String) {
        binding.repoWatchers.text = watchers
    }

    override fun showLink(url: String) {
        binding.repoLink.text = url
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

}