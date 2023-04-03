package com.gb.githubclient.di.user

import com.gb.githubclient.di.repository.RepositorySubcomponent
import com.gb.githubclient.di.user.module.UserModule
import com.gb.githubclient.mvp.presenter.UsersPresenter
import com.gb.githubclient.ui.adapter.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}