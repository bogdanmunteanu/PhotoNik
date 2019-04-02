package com.death.photonik.ui.di.component

import com.death.photonik.data.repository.di.RepositoryModule
import com.death.photonik.di.component.ApplicationComponent
import com.death.photonik.ui.di.ActivityScope

import com.death.photonik.ui.di.module.HomeActivityModule
import com.death.photonik.ui.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [
        RepositoryModule::class,
        HomeActivityModule::class
    ]
)
interface HomeActivityComponent {
    fun inject(activity: HomeActivity)
}