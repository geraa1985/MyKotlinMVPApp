package com.geraa1985.mykotlinmvpapp.di.components

import com.geraa1985.mykotlinmvpapp.di.modules.CiceroneModule
import com.geraa1985.mykotlinmvpapp.mvp.presenter.MainPresenter
import com.geraa1985.mykotlinmvpapp.mvp.presenter.RepoPresenter
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UserPresenter
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UsersPresenter
import com.geraa1985.mykotlinmvpapp.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class
    ]
)
interface AppGraph {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)
}