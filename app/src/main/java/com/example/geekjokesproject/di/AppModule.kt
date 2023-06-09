package com.example.geekjokesproject.di

import android.content.Context
import com.example.geekjokesproject.network.ApiInterface
import com.example.geekjokesproject.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiInterface(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): ApiInterface {
        return remoteDataSource.buildApi(ApiInterface::class.java, context)
    }
}