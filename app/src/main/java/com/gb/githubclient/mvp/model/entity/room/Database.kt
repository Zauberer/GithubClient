package com.gb.githubclient.mvp.model.entity.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gb.githubclient.mvp.model.entity.room.dao.RepositoryDao
import com.gb.githubclient.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}