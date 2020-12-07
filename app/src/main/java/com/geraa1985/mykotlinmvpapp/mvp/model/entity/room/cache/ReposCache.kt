package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache

import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.db.AppDB
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomUserRepo
import io.reactivex.rxjava3.core.Single

class ReposCache: IReposCache {

    override fun putRepos(user: GithubUser, repos: List<UserRepo>) {
        val roomUser = AppDB.getInstance().userDAO.getUser(user.login) ?: throw RuntimeException("No such user in cache")
        AppDB.getInstance().repoDAO.insert(repos.map {
            RoomUserRepo(
                it.id,
                roomUser.id,
                it.name,
                it.language ?: "",
                it.createdAt ?: "",
                it.updatedAt ?: "",
                it.forks ?: 0,
                it.watchers ?: 0,
                it.htmlUrl ?: ""
            )
        })
    }

    override fun getRepos(user: GithubUser): Single<List<UserRepo>> {
        val roomUser = AppDB.getInstance().userDAO.getUser(user.login) ?: throw RuntimeException("No such user in cache")
        return Single.just(AppDB.getInstance().repoDAO.getRepos(roomUser.id).map {
            UserRepo(
                it.id,
                it.name,
                it.language ?: "",
                it.createdAt ?: "",
                it.updatedAt ?: "",
                it.forks ?: 0,
                it.watchers ?: 0,
                it.htmlUrl ?: ""
            )
        })
    }
}