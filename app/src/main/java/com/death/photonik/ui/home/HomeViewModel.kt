package com.death.photonik.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.death.instagram.ui.base.BaseViewModel
import com.death.photonik.data.model.Photos
import com.death.photonik.utils.common.Event
import com.death.photonik.utils.common.Resource

interface HomeViewModel : BaseViewModel{
    val onNetworkResponse:MutableLiveData<Event<Photos>>
    val loading:MutableLiveData<Boolean>
    val onNetworkError:MutableLiveData<Resource<String>>
}