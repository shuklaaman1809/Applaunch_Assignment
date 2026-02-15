package com.example.applaunchdemo.network

import android.content.Context
import androidx.room.Room
import com.example.applaunchdemo.BuildConfig
import com.example.applaunchdemo.db.AppDb
import com.example.applaunchdemo.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDb =
        Room.databaseBuilder(
            context,
            AppDb::class.java,
            "user_db"
        ).build()

    @Provides
    fun provideDao(db: AppDb): UserDao = db.userDao()
}