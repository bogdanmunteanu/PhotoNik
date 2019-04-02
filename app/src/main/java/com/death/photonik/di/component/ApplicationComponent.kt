package com.death.photonik.di.component

import android.app.Application
import android.content.Context
import com.death.photonik.PhotoNikApplication
import com.death.photonik.data.remote.NetworkService
import com.death.photonik.data.remote.di.NetworkModule
import com.death.photonik.data.repository.di.RepositoryModule
import com.death.photonik.di.ApplicationContext
import com.death.photonik.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
@Singleton
interface ApplicationComponent{

    fun inject(app: PhotoNikApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application

    fun getNetworkService(): NetworkService
}