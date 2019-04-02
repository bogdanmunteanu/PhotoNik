package com.death.photonik.utils.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.death.instagram.ui.base.BaseViewModel
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

class ViewModelProviderFactory<VM: BaseViewModel>(
    private val  kClass: KClass<VM>,
    private val creator: () -> VM
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }

}