package com.death.photonik.data.remote.di

import com.death.photonik.data.remote.NetworkService
import com.death.photonik.data.remote.Networking
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule{

    @Provides
    @Singleton
    fun provideNetworkService(
        @NetworkCacheDirectory cacheDir: File,
        @BaseUrl baseUrl: String,
        @NetworkCacheSize cacheSize: Long
    ): NetworkService =
            Networking.create(baseUrl, cacheDir, cacheSize)

}