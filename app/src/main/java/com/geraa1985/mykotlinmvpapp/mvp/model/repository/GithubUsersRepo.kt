package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser

class GithubUsersRepo {

    private val repository = listOf(
        GithubUser("login_1"),
        GithubUser("login_2"),
        GithubUser("login_3"),
        GithubUser("login_4"),
        GithubUser("login_5")
    )

    fun getUsers() = repository
}