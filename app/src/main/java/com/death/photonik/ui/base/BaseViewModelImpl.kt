package com.death.photonik.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.death.instagram.ui.base.BaseViewModel
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModelImpl(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable
) : ViewModel(), BaseViewModel {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected val messageStringIdLiveData: MutableLiveData<Resource<Int>> = MutableLiveData()

    protected val messageLiveData: MutableLiveData<Resource<String>> = MutableLiveData()

    override fun getMessageStringId(): LiveData<Resource<Int>> = messageStringIdLiveData

    override fun getMessage(): LiveData<Resource<String>> = messageLiveData


}