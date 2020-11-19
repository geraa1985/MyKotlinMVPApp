package com.geraa1985.mykotlinmvpapp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface View : MvpView {

    fun init()

    fun updateList()

}