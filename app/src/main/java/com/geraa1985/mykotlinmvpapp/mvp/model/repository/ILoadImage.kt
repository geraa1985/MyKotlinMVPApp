package com.geraa1985.mykotlinmvpapp.mvp.model.repository

interface ILoadImage<T, O> {
    fun loadInto(url: String, container: T, options: O?)
}