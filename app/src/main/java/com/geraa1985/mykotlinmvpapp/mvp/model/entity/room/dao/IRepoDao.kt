package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.dao

import androidx.room.*
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomUserRepo

@Dao
interface IRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RoomUserRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<RoomUserRepo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: RoomUserRepo)

    @Update
    fun update(repo: RoomUserRepo)

    @Update
    fun update(repos: List<RoomUserRepo>)

    @Update
    fun update(vararg repos: RoomUserRepo)

    @Delete
    fun delete(repo: RoomUserRepo)

    @Delete
    fun delete(repos: List<RoomUserRepo>)

    @Delete
    fun delete(vararg repos: RoomUserRepo)

    @Query("SELECT * FROM RoomUserRepo")
    fun getAll(): List<RoomUserRepo>

    @Query("SELECT * FROM RoomUserRepo WHERE uid = :uid")
    fun getRepos(uid: String): List<RoomUserRepo>

}