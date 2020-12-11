package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.dao.IRepoDao
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.dao.IUserDao
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomGithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomUserRepo

@Database(entities = [RoomGithubUser::class, RoomUserRepo::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract val userDAO: IUserDao
    abstract val repoDAO: IRepoDao

    companion object {
        const val NAME_DB = "database.db"
    }
}