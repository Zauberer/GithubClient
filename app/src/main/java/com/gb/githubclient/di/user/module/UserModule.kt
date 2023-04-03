package com.gb.githubclient.di.user.module

import com.gb.githubclient.App
import com.gb.githubclient.di.user.IUserScopeContainer
import com.gb.githubclient.di.user.UserScope
import com.gb.githubclient.mvp.model.api.IDataSource
import com.gb.githubclient.mvp.model.cache.IGithubUsersCache
import com.gb.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.gb.githubclient.mvp.model.entity.room.Database
import com.gb.githubclient.mvp.model.network.INetworkStatus
import com.gb.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
open class UserModule {

    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus, cache)
    }

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}