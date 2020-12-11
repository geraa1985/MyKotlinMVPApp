package com.geraa1985.mykotlinmvpapp.di.modules

import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubReposRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.GithubUsersRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.IReposRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.IUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Singleton
    @Provides
    fun usersRepo(): IUsersRepo = GithubUsersRepo()

    @Singleton
    @Provides
    fun reposRepo(): IReposRepo = GithubReposRepo()

}