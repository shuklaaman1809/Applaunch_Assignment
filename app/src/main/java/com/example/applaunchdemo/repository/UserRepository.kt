package com.example.applaunchdemo.repository

import android.util.Log
import com.example.applaunchdemo.db.UserDao
import com.example.applaunchdemo.db.UserEntity
import com.example.applaunchdemo.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiService,
    private val dao: UserDao
) {

    val usersFlow: Flow<List<UserEntity>> = dao.getUsers()

    fun getUserById(id: Int): Flow<UserEntity?> =
        dao.getUserById(id)

    suspend fun syncUsersInBackground() {
        try {
            val users = api.getUsers().map {
                UserEntity(
                    id = it.id,
                    name = it.name,
                    username = it.username,
                    email = it.email,
                    phone = it.phone,
                    website = it.website
                )
            }
            dao.insertUsers(users)
        } catch (e: Exception) {
            Log.e("UserRepository", "Error syncing users", e)
        }
    }

    suspend fun deleteUser(user: UserEntity) =
        dao.deleteUser(user)

    suspend fun updateUser(user: UserEntity) =
        dao.updateUser(user)
}