package com.geraa1985.mykotlinmvpapp.di.modules

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.mykotlinmvpapp.mvp.model.repository.*
import com.geraa1985.mykotlinmvpapp.ui.image.GlideImgLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Singleton
    @Provides
    fun usersRepo(): IUsersRepo = GithubUsersRepo()

    @Singleton
    @Provides
    fun reposRepo(): IReposRepo = GithubReposRepo()

    @Singleton
    @Provides
    fun imgRepo(): ILoadImage<ImageView, RequestOptions> = GlideImgLoader()

}