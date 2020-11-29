package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo {

    private val users = listOf(
        GithubUser("login_1"),
        GithubUser("login_2"),
        GithubUser("login_3"),
        GithubUser("login_4"),
        GithubUser("login_5")
    )

    fun getUsers(): Single<List<GithubUser>> = Single.create<List<GithubUser>> {
        it.onSuccess(users)
    }.subscribeOn(Schedulers.io())
}