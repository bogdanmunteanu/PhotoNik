package com.death.photonik

import android.app.Application
import com.death.photonik.di.injector.ApplicationInjector
import timber.log.Timber

class PhotoNikApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        ApplicationInjector.inject(this)
        plantTimber()
    }

    fun plantTimber(){
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}