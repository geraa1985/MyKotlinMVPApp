package com.geraa1985.mykotlinmvpapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserView: MvpView {
    fun showLogin(login: String)
    fun showAvatar(url: String)
    fun initRvRepos()
    fun updateReposList()
    fun showError(message: String)
    fun setUser()
}