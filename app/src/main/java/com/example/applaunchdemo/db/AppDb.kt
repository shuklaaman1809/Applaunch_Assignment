package com.example.applaunchdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}