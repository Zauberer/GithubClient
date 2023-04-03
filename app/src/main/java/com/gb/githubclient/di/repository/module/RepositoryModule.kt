package com.gb.githubclient.di.repository.module

import com.gb.githubclient.App
import com.gb.githubclient.di.repository.IRepositoryScopeContainer
import com.gb.githubclient.di.repository.RepositoryScope
import com.gb.githubclient.mvp.model.api.IDataSource
import com.gb.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.gb.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.gb.githubclient.mvp.model.entity.room.Database
import com.gb.githubclient.mvp.model.network.INetworkStatus
import com.gb.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo {
        return RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
    }

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}