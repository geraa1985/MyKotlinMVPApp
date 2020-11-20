package com.geraa1985.mykotlinmvpapp.mvp.view

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserView: MvpView {
    fun init(user: GithubUser)
}