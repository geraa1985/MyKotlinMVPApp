package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.dao

import androidx.room.*
import com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities.RoomGithubUser

@Dao
interface IUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUser)

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(users: List<RoomGithubUser>)

    @Update
    fun update(vararg users: RoomGithubUser)

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    @Delete
    fun delete(vararg users: RoomGithubUser)

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun getUser(login: String): RoomGithubUser?

    @Query("SELECT * FROM RoomGithubUser WHERE login LIKE '%' || :login  || '%'")
    fun getUsersByLogin(login: String): List<RoomGithubUser>

}