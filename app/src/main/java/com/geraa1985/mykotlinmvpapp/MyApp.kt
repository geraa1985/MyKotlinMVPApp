package com.geraa1985.mykotlinmvpapp

import android.app.Application
import com.geraa1985.mykotlinmvpapp.di.components.AppGraph
import com.geraa1985.mykotlinmvpapp.di.components.DaggerAppGraph
import com.geraa1985.mykotlinmvpapp.di.modules.AppModule

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
    }

    lateinit var appGraph: AppGraph

    override fun onCreate() {
        super.onCreate()
        instance = this
        appGraph = DaggerAppGraph.builder()
            .appModule(AppModule(this))
            .build()
    }
}