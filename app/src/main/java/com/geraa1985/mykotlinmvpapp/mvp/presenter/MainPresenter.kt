package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.mvp.view.IMainView
import com.geraa1985.mykotlinmvpapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router): MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootChain(Screens.usersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
