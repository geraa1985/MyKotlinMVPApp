package com.geraa1985.mykotlinmvpapp.di.modules

import com.geraa1985.mykotlinmvpapp.mvp.presenter.MainPresenter
import com.geraa1985.mykotlinmvpapp.mvp.presenter.RepoPresenter
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UserPresenter
import com.geraa1985.mykotlinmvpapp.mvp.presenter.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    fun mainPresenter(): MainPresenter = MainPresenter()

    @Provides
    fun usersPresenter(): UsersPresenter = UsersPresenter()

    @Provides
    fun userPresenter(): UserPresenter = UserPresenter()

    @Provides
    fun repoPresenter(): RepoPresenter = RepoPresenter()

}