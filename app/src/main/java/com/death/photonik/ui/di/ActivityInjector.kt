package com.death.photonik.ui.di

import com.death.photonik.di.component.ApplicationComponent
import com.death.photonik.di.injector.ApplicationInjector
import com.death.photonik.ui.base.BaseActivity
import com.death.photonik.ui.di.component.DaggerHomeActivityComponent
import com.death.photonik.ui.di.module.HomeActivityModule
import com.death.photonik.ui.home.HomeActivity

object ActivityInjector{

    fun<A: BaseActivity<*,*>> inject(activity: A){
        when(activity){
            is HomeActivity-> DaggerHomeActivityComponent.builder()
                .applicationComponent(ApplicationInjector.applicationComponent)
                .homeActivityModule(HomeActivityModule(activity))
                .build().inject(activity)
        }
    }

}