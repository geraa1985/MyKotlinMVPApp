package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser (
    @PrimaryKey val id: String,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?
)