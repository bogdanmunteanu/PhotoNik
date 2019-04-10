package com.death.photonik.ui.upload

import androidx.lifecycle.MutableLiveData
import com.death.instagram.ui.base.BaseViewModel
import com.death.photonik.utils.common.Resource


interface UploadVModel : BaseViewModel {
    fun uploadPhoto()
    val uploadStatus: MutableLiveData<Resource<Any>>
    val uploadTitle: MutableLiveData<String>
    val uploadImageUrl: MutableLiveData<String>
    val uploadImageDescription: MutableLiveData<String>
    val loading: MutableLiveData<Boolean>
}