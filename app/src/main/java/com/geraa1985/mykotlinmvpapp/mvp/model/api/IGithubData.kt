package com.geraa1985.mykotlinmvpapp.mvp.model.api

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.SearchResult
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IGithubData {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<UserRepo>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

    @GET("search/users")
    fun searchUsers(@Query("q") login: String ): Single<SearchResult>

}