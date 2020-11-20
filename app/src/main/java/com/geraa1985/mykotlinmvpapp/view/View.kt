package com.geraa1985.mykotlinmvpapp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface View : MvpView {

    fun setTextForButton1(value: String)

    fun setTextForButton2(value: String)

    fun setTextForButton3(value: String)

}