package com.geraa1985.mykotlinmvpapp.di.modules

import androidx.room.Room
import com.geraa1985.mykotlinmvpapp.MyApp
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.IReposCache
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.IUsersCache
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.ReposCache
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.cache.UsersCache
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.db.AppDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(app: MyApp): AppDB = Room.databaseBuilder(
        app,
        AppDB::class.java,
        AppDB.NAME_DB
    ).build()

    @Singleton
    @Provides
    fun usersCache(): IUsersCache = UsersCache()

    @Singleton
    @Provides
    fun reposCache(): IReposCache = ReposCache()

}