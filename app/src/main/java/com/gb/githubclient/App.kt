package com.gb.githubclient

import android.app.Application
import com.gb.githubclient.di.AppComponent
import com.gb.githubclient.di.DaggerAppComponent
import com.gb.githubclient.di.module.AppModule
import com.gb.githubclient.di.repository.IRepositoryScopeContainer
import com.gb.githubclient.di.repository.RepositorySubcomponent
import com.gb.githubclient.di.user.IUserScopeContainer
import com.gb.githubclient.di.user.UserSubcomponent

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
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

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}