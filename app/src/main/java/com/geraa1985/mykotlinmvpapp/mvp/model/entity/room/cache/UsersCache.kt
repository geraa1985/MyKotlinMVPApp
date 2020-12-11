package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache

import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.GithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.db.AppDB
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersCache : IUsersCache {

    @Inject
    lateinit var db: AppDB

    init {
        MyApp.instance.appGraph.inject(this)
    }

    override fun putUsers(users: List<GithubUser>) {
        val roomUsers = users.map {
            RoomGithubUser(
                it.id,
                it.login,
                it.avatarUrl ?: "",
                it.reposUrl ?: ""
            )
        }
        db.userDAO.insert(roomUsers)
    }

    override fun getUsers(): Single<List<GithubUser>> =
        Single.just(db.userDAO.getAll().map {
            GithubUser(
                it.id,
                it.login,
                it.avatarUrl ?: "",
                it.reposUrl ?: ""
            )
        })

    override fun getUsersByLogin(login: String): Single<List<GithubUser>> =
        Single.just(db.userDAO.getUsersByLogin(login).map {
            GithubUser(
                it.id,
                it.login,
                it.avatarUrl ?: "",
                it.reposUrl ?: ""
            )
        })

    override fun putUser(user: GithubUser) {
        val roomUser = user.let {
            RoomGithubUser(
                it.id,
                it.login,
                it.avatarUrl ?: "",
                it.reposUrl ?: ""
            )
        }
        db.userDAO.insert(roomUser)
    }

    override fun getUser(login: String): Single<GithubUser> =
        Single.just(db.userDAO.getUser(login)?.let {
            GithubUser(
                it.id,
                it.login,
                it.avatarUrl ?: "",
                it.reposUrl ?: ""
            )
        })

}