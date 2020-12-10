package com.geraa1985.mykotlinmvpapp.mvp.model.repository

import com.geraa1985.mykotlinmvpapp.mvp.model.api.IGithubData
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.IUsersCache
import com.geraa1985.mykotlinmvpapp.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    private val api: IGithubData,
    private val networkStatus: INetworkStatus,
    private val usersCache: IUsersCache
) : IUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    Single.just(users)
                }
            } else {
                usersCache.getUsers()
            }
        }.subscribeOn(Schedulers.io())

    override fun getUser(login: String): Single<GithubUser> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUser(login).flatMap { user ->
                    Single.just(user)
                }
            } else {
                usersCache.getUser(login)
            }
        }.subscribeOn(Schedulers.io())

    override fun searchUsers(login: String): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.searchUsers(login).flatMap { result ->
                    Single.just(result.items)
                }
            } else {
                usersCache.getUsersByLogin(login)
            }
        }.subscribeOn(Schedulers.io())

    override fun putUser(user: GithubUser) {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                usersCache.putUser(user)
            }
        }
    }
}