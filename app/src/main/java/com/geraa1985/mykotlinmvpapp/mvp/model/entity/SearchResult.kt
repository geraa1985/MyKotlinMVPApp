package com.geraa1985.mykotlinmvpapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    @Expose val items: List<GithubUser>
) : Parcelable