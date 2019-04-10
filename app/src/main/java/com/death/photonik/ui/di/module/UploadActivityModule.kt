package com.death.photonik.ui.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.data.repository.PhotoRepository
import com.death.photonik.ui.base.BaseActivity
import com.death.photonik.ui.home.HomeViewModel
import com.death.photonik.ui.home.HomeViewModelImpl
import com.death.photonik.ui.home.HomeActivity
import com.death.photonik.ui.home.adapter.PhotoAdapter
import com.death.photonik.ui.upload.UploadActivity
import com.death.photonik.ui.upload.UploadVModel
import com.death.photonik.ui.upload.UploadViewModelImpl
import com.death.photonik.utils.common.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class UploadActivityModule(private val activity: UploadActivity): BaseActivityModule(activity){

    @Provides
    fun provideUploadViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        photoRepository: PhotoRepository
    ):UploadVModel = ViewModelProviders.of(activity, ViewModelProviderFactory(UploadViewModelImpl::class){
        UploadViewModelImpl(schedulerProvider, compositeDisposable, photoRepository)
    }).get(UploadViewModelImpl::class.java)

}