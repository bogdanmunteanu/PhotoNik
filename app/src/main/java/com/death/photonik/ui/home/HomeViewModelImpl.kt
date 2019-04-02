package com.death.photonik.ui.home

import androidx.lifecycle.MutableLiveData
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.data.model.Photos
import com.death.photonik.data.repository.PhotoRepository
import com.death.photonik.ui.base.BaseViewModelImpl
import com.death.photonik.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable

class HomeViewModelImpl constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val photoRepository: PhotoRepository
) : BaseViewModelImpl(schedulerProvider, compositeDisposable), HomeViewModel {
    override val onNetworkError: MutableLiveData<Resource<String>> = MutableLiveData()
    override val loading: MutableLiveData<Boolean> = MutableLiveData()
    override val onNetworkResponse: MutableLiveData<Photos> = MutableLiveData()

    override fun onViewCreated() {
        getPhotos()
    }


    fun getPhotos() {
        loading.postValue(true)
        compositeDisposable.add(
            photoRepository.getPhoto()
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    loading.postValue(false)
                    onNetworkResponse.postValue(it)
                }, {
                    loading.postValue(false)
                    onNetworkError.postValue(Resource.error("Something went wrong"))
                })
        )
    }


}