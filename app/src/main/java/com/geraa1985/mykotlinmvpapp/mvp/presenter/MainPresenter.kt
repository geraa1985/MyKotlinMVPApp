package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.view.IMainView
import com.geraa1985.mykotlinmvpapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<IMainView>() {

    @Inject
    lateinit var router: Router

    init {
        MyApp.instance.appGraph.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootChain(Screens.usersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
