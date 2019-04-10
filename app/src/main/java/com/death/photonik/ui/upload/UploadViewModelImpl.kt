package com.death.photonik.ui.upload

import androidx.lifecycle.MutableLiveData
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.data.model.AddPhotoRequest
import com.death.photonik.data.repository.PhotoRepository
import com.death.photonik.ui.base.BaseViewModelImpl
import com.death.photonik.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable

class UploadViewModelImpl constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val photoRepository: PhotoRepository
) : BaseViewModelImpl(schedulerProvider, compositeDisposable), UploadVModel {

    override val uploadTitle: MutableLiveData<String> = MutableLiveData()
    override val uploadImageUrl: MutableLiveData<String> = MutableLiveData()
    override val uploadImageDescription: MutableLiveData<String> = MutableLiveData()
    override val uploadStatus: MutableLiveData<Resource<Any>> = MutableLiveData()
    override val loading: MutableLiveData<Boolean> = MutableLiveData()


    override fun onViewCreated() {

    }

    override fun uploadPhoto() {
        when {
            uploadTitle.value==null -> messageLiveData.postValue(Resource.error("Enter Valid Name"))
            uploadImageDescription.value==null -> messageLiveData.postValue(Resource.error("Enter Valid Description"))
            uploadImageUrl.value==null -> messageLiveData.postValue(Resource.error("Enter Valid Image URL"))
            else -> {
                loading.postValue(true)
                photoRepository.uploadPhoto(AddPhotoRequest(uploadImageDescription.value.toString(), uploadImageUrl.value.toString(), uploadTitle.value.toString()))
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        loading.postValue(false)
                        messageLiveData.postValue(Resource.success("Photo Added to Server"))
                        uploadTitle.postValue("")
                        uploadImageDescription.postValue("")
                        uploadImageUrl.postValue("")
                    },{
                        loading.postValue(false)
                        it.printStackTrace()
                        messageLiveData.postValue(Resource.success("Failed to add photo"))
                    })
            }
        }
    }

}