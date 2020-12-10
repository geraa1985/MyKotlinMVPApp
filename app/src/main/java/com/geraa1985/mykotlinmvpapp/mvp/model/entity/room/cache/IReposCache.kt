package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import io.reactivex.rxjava3.core.Single

interface IReposCache {
    fun putRepos(user:GithubUser, repos: List<UserRepo>)
    fun getRepos(user: GithubUser): Single<List<UserRepo>>
}