package com.death.photonik.ui.di.module

import android.content.Context
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.ui.base.BaseActivity
import com.death.photonik.ui.di.ActivityContext
import com.death.photonik.utils.rx.RxSchedulerProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class BaseActivityModule(private val activity: BaseActivity<*, *>)
{
    @Provides
    @ActivityContext
    fun provideContext():Context = activity

    @Provides
    fun provideActivity(): BaseActivity<*,*> = activity

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider() : SchedulerProvider = RxSchedulerProviderImpl()

}