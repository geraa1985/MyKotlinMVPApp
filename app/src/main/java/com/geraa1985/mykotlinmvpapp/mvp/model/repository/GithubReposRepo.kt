package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.api.IGithubData
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.IReposCache
import com.geraa1985.mykotlinmvpapp.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GithubReposRepo: IReposRepo {

    @Inject
    lateinit var networkStatus: INetworkStatus
    @Inject
    lateinit var api: IGithubData
    @Inject
    lateinit var reposCache: IReposCache

    init {
        MyApp.instance.appGraph.inject(this)
    }


    override fun getRepos(user: GithubUser): Single<List<UserRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { repoUrl ->
                    api.getUserRepos(repoUrl).flatMap { userRepos ->
                        reposCache.putRepos(user, userRepos)
                        Single.just(userRepos)
                    } ?: Single.error(RuntimeException("User has no repos url"))
                }
            } else {
                reposCache.getRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}