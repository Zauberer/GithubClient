package com.gb.githubclient.mvp.model.repo.retrofit

import com.gb.githubclient.mvp.model.api.IDataSource
import com.gb.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.gb.githubclient.mvp.model.entity.GithubRepository
import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.model.network.INetworkStatus
import com.gb.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubRepositoriesCache)
    : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getRepositories(url)
                    .flatMap { repositories ->
                        cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            cache.getUserRepos(user)
        }
    }.subscribeOn(Schedulers.io())
}