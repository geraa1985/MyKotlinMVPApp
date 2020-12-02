package com.geraa1985.mykotlinmvpapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface IRepoView: MvpView {

    fun showName(name: String)
    fun showLang(lang: String)
    fun showCreated(createDate: String)
    fun showUpdated(updateDate: String)
    fun showForks(forks: String)
    fun showWatchers(watchers: String)
    fun showLink(url: String)

}