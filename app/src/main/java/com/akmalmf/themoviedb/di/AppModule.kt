package com.akmalmf.themoviedb.di

import com.akmalmf.themoviedb.repository.MainRepository
import com.akmalmf.themoviedb.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:14
 * akmalmf007@gmail.com
 */
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesMainRepository(api: ApiService): MainRepository {
        return MainRepository(api)
    }
}