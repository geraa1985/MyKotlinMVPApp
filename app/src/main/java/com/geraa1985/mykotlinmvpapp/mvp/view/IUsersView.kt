package com.geraa1985.mykotlinmvpapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUsersView : MvpView {

    fun initRvUsers()

    fun updateUsersList()

    fun showError(message: String)

}