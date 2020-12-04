package com.geraa1985.mykotlinmvpapp.mvp.presenter

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.api.ApiHolder
import com.geraa1985.mykotlinmvpapp.mvp.view.IMainView
import com.geraa1985.mykotlinmvpapp.navigation.Screens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootChain(Screens.usersScreen())
    }

    private val compositeDisposable = CompositeDisposable()

    fun searchUser(login: String?) {
        login?.let { userLogin ->
            val disposable1 = ApiHolder.api.getUser(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    user?.let { MyApp.instance.router.navigateTo(Screens.userScreen(user)) }
                }, { error ->
                    error.message?.let { viewState.showError(it) }
                })
            compositeDisposable.add(disposable1)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    fun backClicked() {
        router.exit()
    }
}
