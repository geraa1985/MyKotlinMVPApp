package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.view.IUserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: GithubUser?, private val router: Router): MvpPresenter<IUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        showLogin()
    }

    private fun showLogin() {
        user?.let {
            viewState.showLogin(it.login)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}