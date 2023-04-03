package com.gb.githubclient.di

import com.gb.githubclient.di.module.*
import com.gb.githubclient.di.user.UserSubcomponent
import com.gb.githubclient.mvp.presenter.MainPresenter
import com.gb.githubclient.mvp.presenter.UsersPresenter
import com.gb.githubclient.ui.activity.MainActivity
import com.gb.githubclient.ui.fragment.RepositoryFragment
import com.gb.githubclient.ui.fragment.UserFragment
import com.gb.githubclient.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent() : UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}