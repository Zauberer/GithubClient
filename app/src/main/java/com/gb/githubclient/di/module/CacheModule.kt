package com.gb.githubclient.di.module

import androidx.room.Room
import com.gb.githubclient.App
import com.gb.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.gb.githubclient.mvp.model.cache.IGithubUsersCache
import com.gb.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.gb.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.gb.githubclient.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()
}