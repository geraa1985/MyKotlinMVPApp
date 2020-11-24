package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.annotations.SchedulerSupport.IO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler

class GithubUsersRepo {

    private val repository = listOf(
        GithubUser("login_1"),
        GithubUser("login_2"),
        GithubUser("login_3"),
        GithubUser("login_4"),
        GithubUser("login_5")
    )

    fun getUsers(): Observable<GithubUser> = Observable.fromIterable(repository)
}