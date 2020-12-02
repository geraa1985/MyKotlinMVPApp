package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getRepos(url: String): Single<List<UserRepo>>
}