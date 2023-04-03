package com.gb.githubclient.mvp.model.cache.room

import com.gb.githubclient.mvp.model.cache.IGithubUsersCache
import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.model.entity.room.Database
import com.gb.githubclient.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(val db: Database) : IGithubUsersCache {

    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }

    override fun putUsers(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login , user.avatarUrl ?: "", user.reposUrl ?: "")
        }

        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}