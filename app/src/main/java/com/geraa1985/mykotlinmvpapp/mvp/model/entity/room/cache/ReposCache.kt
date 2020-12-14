package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.UserRepo
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.db.AppDB
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomUserRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ReposCache: IReposCache {

    @Inject
    lateinit var db: AppDB

    init {
        MyApp.instance.appGraph.inject(this)
    }

    override fun putRepos(user: GithubUser, repos: List<UserRepo>) {
        val roomUser = db.userDAO.getUser(user.login) ?: throw RuntimeException("No such user in cache")
        db.repoDAO.insert(repos.map {
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
        val roomUser = db.userDAO.getUser(user.login) ?: throw RuntimeException("No such user in cache")
        return Single.just(db.repoDAO.getRepos(roomUser.id).map {
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