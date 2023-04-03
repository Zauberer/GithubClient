package com.gb.githubclient

import android.app.Application
import com.gb.githubclient.di.AppComponent
import com.gb.githubclient.di.DaggerAppComponent
import com.gb.githubclient.di.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}