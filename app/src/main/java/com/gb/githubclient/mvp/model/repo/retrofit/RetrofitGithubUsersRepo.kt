package com.gb.githubclient.mvp.model.repo.retrofit

import com.gb.githubclient.mvp.model.api.IDataSource
import com.gb.githubclient.mvp.model.cache.IGithubUsersCache
import com.gb.githubclient.mvp.model.network.INetworkStatus
import com.gb.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubUsersCache)
    : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cache.putUsers(users).toSingleDefault(users)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}