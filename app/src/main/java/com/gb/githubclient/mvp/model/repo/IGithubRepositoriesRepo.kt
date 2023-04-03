package com.gb.githubclient.mvp.model.repo

import com.gb.githubclient.mvp.model.entity.GithubRepository
import com.gb.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}