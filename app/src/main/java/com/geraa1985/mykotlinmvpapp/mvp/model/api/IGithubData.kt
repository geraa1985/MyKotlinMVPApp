package com.geraa1985.mykotlinmvpapp.mvp.model.api

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IGithubData {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<UserRepo>>

}