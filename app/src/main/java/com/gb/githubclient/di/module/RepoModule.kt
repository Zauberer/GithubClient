package com.gb.githubclient.di.module

import com.gb.githubclient.mvp.model.api.IDataSource
import com.gb.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.gb.githubclient.mvp.model.cache.IGithubUsersCache
import com.gb.githubclient.mvp.model.network.INetworkStatus
import com.gb.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.gb.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache)
        : IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache)
        : IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}