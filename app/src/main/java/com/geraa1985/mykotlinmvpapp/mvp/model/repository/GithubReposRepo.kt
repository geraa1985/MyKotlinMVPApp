package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.api.IGithubData
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.ReposCache
import com.geraa1985.mykotlinmvpapp.ui.networkstatus.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubReposRepo(
    private val api: IGithubData,
    private val networkStatus: NetworkStatus,
    private val reposCache: ReposCache
): IReposRepo {
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