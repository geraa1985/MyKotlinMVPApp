package com.geraa1985.mykotlinmvpapp.mvp.model

interface ILoadImage<T> {
    fun loadInto(url: String, container: T)
}