package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.view.IRepoView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoPresenter: MvpPresenter<IRepoView>() {

    @Inject
    lateinit var router: Router

    private lateinit var repo: UserRepo

    init {
        MyApp.instance.appGraph.inject(this)
        viewState.setRepo()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        showName()
        showLang()
        showCreateDate()
        showUpdateDate()
        showForks()
        showWatchers()
        showLink()
    }

    fun setRepo(repo: UserRepo?) {
        repo?.let { this.repo = it }
    }

    private fun showName() {
        repo.let { viewState.showName(it.name) }
    }

    private fun showLang() {
        repo.language?.let { viewState.showLang(it) }
    }

    private fun showCreateDate() {
        repo.createdAt?.let { viewState.showCreated(it) }
    }

    private fun showUpdateDate() {
        repo.updatedAt?.let { viewState.showUpdated(it) }
    }

    private fun showForks() {
        repo.forks?.let { viewState.showForks(it.toString()) }
    }

    private fun showWatchers() {
        repo.watchers?.let { viewState.showWatchers(it.toString()) }
    }

    private fun showLink() {
        repo.htmlUrl?.let { viewState.showLink(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}