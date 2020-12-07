package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.dao.IRepoDao
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.dao.IUserDao
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomGithubUser
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomUserRepo

@Database(entities = [RoomGithubUser::class, RoomUserRepo::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract val userDAO: IUserDao
    abstract val repoDAO: IRepoDao

    companion object {
        private const val NAME_DB = "database.db"
        private val instance: AppDB? = null
        fun getInstance() = instance ?: Room.databaseBuilder(
            MyApp.instance.applicationContext,
            AppDB::class.java,
            NAME_DB
        ).build()
    }
}