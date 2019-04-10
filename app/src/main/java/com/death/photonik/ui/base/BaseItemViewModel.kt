package com.death.photonik.ui.base

abstract class BaseItemViewModel<T: Any>(var data:T? = null)
{
    fun updateData(data:T){
        this.data = data
    }
}