package com.geraa1985.mykotlinmvpapp.mvp.model.entity.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomUserRepo(
    @PrimaryKey val id: String,
    val uid: String,
    val name: String,
    val language: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val forks: Int?,
    val watchers: Int?,
    val htmlUrl: String?
)
