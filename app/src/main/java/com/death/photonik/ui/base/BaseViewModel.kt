package com.death.instagram.ui.base

import androidx.lifecycle.LiveData
import com.death.photonik.utils.common.Resource

interface BaseViewModel{

    fun getMessageStringId(): LiveData<Resource<Int>>

    fun getMessage(): LiveData<Resource<String>>

    fun onViewCreated()

}