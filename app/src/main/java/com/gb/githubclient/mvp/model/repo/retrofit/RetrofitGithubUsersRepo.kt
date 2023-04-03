package com.gb.githubclient.mvp.model.repo.retrofit

import com.gb.githubclient.mvp.model.api.IDataSource
import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.model.entity.room.Database
import com.gb.githubclient.mvp.model.entity.room.RoomGithubUser
import com.gb.githubclient.mvp.model.network.INetworkStatus
import com.gb.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database): IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(user.id ?: "",
                                         user.login ?: "",
                                      user.avatarUrl ?: "",
                                      user.reposUrl ?: "")
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}