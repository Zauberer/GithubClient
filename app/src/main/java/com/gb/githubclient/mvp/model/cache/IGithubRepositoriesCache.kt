package com.gb.githubclient.mvp.model.cache

import com.gb.githubclient.mvp.model.entity.GithubRepository
import com.gb.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
    fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
}