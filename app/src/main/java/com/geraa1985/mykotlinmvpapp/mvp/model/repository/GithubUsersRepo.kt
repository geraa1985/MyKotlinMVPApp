package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.api.IGithubData
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.UsersCache
import com.geraa1985.mykotlinmvpapp.ui.networkstatus.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    private val api: IGithubData,
    private val networkStatus: NetworkStatus,
    private val usersCache: UsersCache
) : IUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    usersCache.putUsers(users)
                    Single.just(users)
                }
            } else {
                usersCache.getUsers()
            }
        }.subscribeOn(Schedulers.io())
}