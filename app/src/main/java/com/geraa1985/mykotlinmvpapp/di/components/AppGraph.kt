package com.geraa1985.mykotlinmvpapp.di.components

import com.geraa1985.mykotlinmvpapp.di.modules.*
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.ReposCache
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.UsersCache
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubReposRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubUsersRepo
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
        CiceroneModule::class,
        ReposModule::class,
        NetworkModule::class,
        CacheModule::class,
        AppModule::class
    ]
)
interface AppGraph {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)
    fun inject(githubUsersRepo: GithubUsersRepo)
    fun inject(githubReposRepo: GithubReposRepo)
    fun inject(usersCache: UsersCache)
    fun inject(reposCache: ReposCache)
}