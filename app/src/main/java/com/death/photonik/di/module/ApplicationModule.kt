package com.death.photonik.di.module

import android.app.Application
import android.content.Context
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.BuildConfig
import com.death.photonik.PhotoNikApplication
import com.death.photonik.data.remote.di.BaseUrl
import com.death.photonik.data.remote.di.NetworkCacheDirectory
import com.death.photonik.data.remote.di.NetworkCacheSize
import com.death.photonik.di.ApplicationContext
import com.death.photonik.utils.rx.RxSchedulerProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: PhotoNikApplication){
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @NetworkCacheDirectory
    fun provideNetworkCacheDirectory(): File = application.cacheDir

    @Provides
    @Singleton
    @NetworkCacheSize
    fun provideNetworkCacheSize(): Long = 10 * 1024 * 1024 // 10MB


    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProviderImpl()


}