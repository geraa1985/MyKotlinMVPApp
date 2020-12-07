package com.geraa1985.mykotlinmvpapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepo(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val language: String?,
    @Expose val createdAt: String?,
    @Expose val updatedAt: String?,
    @Expose val forks: Int?,
    @Expose val watchers: Int?,
    @Expose val htmlUrl: String?
) : Parcelable
