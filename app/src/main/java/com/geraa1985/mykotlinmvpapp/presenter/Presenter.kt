package com.geraa1985.mykotlinmvpapp.presenter

import com.geraa1985.mykotlinmvpapp.model.Model
import com.geraa1985.mykotlinmvpapp.view.View

class Presenter(private val model: Model, private val view: View) {

    fun clickOnButton1() {
        model.counters[0]++
        view.setTextForButton1(model.counters[0].toString())
    }

    fun clickOnButton2() {
        model.counters[1]++
        view.setTextForButton2(model.counters[1].toString())
    }

    fun clickOnButton3() {
        model.counters[2]++
        view.setTextForButton3(model.counters[2].toString())
    }

}