package com.geraa1985.mykotlinmvpapp.mvp.model.api

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IGithubData {

    @GET("users")
    fun getUsers():Single<List<GithubUser>>

}