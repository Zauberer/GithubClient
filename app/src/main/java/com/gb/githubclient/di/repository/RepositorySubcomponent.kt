package com.gb.githubclient.di.repository

import com.gb.githubclient.di.repository.module.RepositoryModule
import com.gb.githubclient.mvp.presenter.RepositoryPresenter
import com.gb.githubclient.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}