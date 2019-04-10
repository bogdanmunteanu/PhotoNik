package com.death.photonik.ui.di

import com.death.photonik.di.injector.ApplicationInjector
import com.death.photonik.ui.base.BaseActivity
import com.death.photonik.ui.di.component.DaggerHomeActivityComponent
import com.death.photonik.ui.di.component.DaggerUploadActivityComponent
import com.death.photonik.ui.di.module.HomeActivityModule
import com.death.photonik.ui.di.module.UploadActivityModule
import com.death.photonik.ui.home.HomeActivity
import com.death.photonik.ui.upload.UploadActivity

object ActivityInjector{

    fun<A: BaseActivity<*,*>> inject(activity: A){
        when(activity){
            is HomeActivity-> DaggerHomeActivityComponent.builder()
                .applicationComponent(ApplicationInjector.applicationComponent)
                .homeActivityModule(HomeActivityModule(activity))
                .build().inject(activity)
            is UploadActivity-> DaggerUploadActivityComponent.builder()
                .applicationComponent(ApplicationInjector.applicationComponent)
                .uploadActivityModule(UploadActivityModule(activity))
                .build().inject(activity)
        }
    }

}