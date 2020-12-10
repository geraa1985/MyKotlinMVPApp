package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun putUsers(users: List<GithubUser>)
    fun getUsers(): Single<List<GithubUser>>
    fun putUser(user: GithubUser)
    fun getUser(login: String): Single<GithubUser>
    fun getUsersByLogin(login: String): Single<List<GithubUser>>
}