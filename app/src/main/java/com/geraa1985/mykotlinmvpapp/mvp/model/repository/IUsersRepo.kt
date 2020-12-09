package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUser(login: String): Single<GithubUser>
    fun searchUsers(login: String): Single<List<GithubUser>>
    fun putUser(user: GithubUser)
}