package com.death.photonik.ui.home

import androidx.lifecycle.MutableLiveData
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.data.model.Photos
import com.death.photonik.data.repository.PhotoRepository
import com.death.photonik.ui.base.BaseViewModelImpl
import com.death.photonik.utils.common.Event
import com.death.photonik.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicBoolean

class HomeViewModelImpl constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val photoRepository: PhotoRepository
) : BaseViewModelImpl(schedulerProvider, compositeDisposable), HomeViewModel {
    override val onNetworkError: MutableLiveData<Resource<String>> = MutableLiveData()
    override val loading: MutableLiveData<Boolean> = MutableLiveData()
    override val onNetworkResponse: MutableLiveData<Event<Photos>> = MutableLiveData()
    val isHandled = AtomicBoolean(false)
    override fun onViewCreated() {
        if(!isHandled.get()){
            getPhotos()
        }
    }

    fun getPhotos() {
        loading.postValue(true)
        compositeDisposable.add(
            photoRepository.getPhoto()
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    isHandled.set(true)
                    loading.postValue(false)
                    onNetworkResponse.postValue(Event(it))
                }, {
                    loading.postValue(false)
                    onNetworkError.postValue(Resource.error("Something went wrong"))
                })
        )
    }


}