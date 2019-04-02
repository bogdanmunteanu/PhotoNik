package com.death.photonik.di.injector

import com.death.photonik.PhotoNikApplication
import com.death.photonik.data.remote.di.NetworkModule
import com.death.photonik.di.component.ApplicationComponent
import com.death.photonik.di.component.DaggerApplicationComponent
import com.death.photonik.di.module.ApplicationModule

object ApplicationInjector{

    lateinit var applicationComponent: ApplicationComponent

    fun inject(application: PhotoNikApplication){
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(application))
            .networkModule(NetworkModule())
            .build()
        applicationComponent.inject(application)
    }

}