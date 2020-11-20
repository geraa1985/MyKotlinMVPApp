package com.geraa1985.mykotlinmvpapp.presenter

import com.geraa1985.mykotlinmvpapp.model.Model
import com.geraa1985.mykotlinmvpapp.view.View
import moxy.MvpPresenter

class Presenter(private val model: Model) : MvpPresenter<View>() {

    fun clickOnButton1() {
        model.counters[0]++
        viewState.setTextForButton1(model.counters[0].toString())
    }

    fun clickOnButton2() {
        model.counters[1]++
        viewState.setTextForButton2(model.counters[1].toString())
    }

    fun clickOnButton3() {
        model.counters[2]++
        viewState.setTextForButton3(model.counters[2].toString())
    }

}