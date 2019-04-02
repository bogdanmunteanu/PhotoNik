package com.death.photonik.utils.common

data class Resource<out T> constructor(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T ? = null): Resource<T> = Resource(Status.SUCCESS, data)

        fun <T> error(data: T? = null): Resource<T> = Resource(Status.ERROR, data)

        fun <T> loading(data: T? = null): Resource<T> = Resource(Status.LOADING, data)

        fun <T> unknown(data: T? = null): Resource<T> = Resource(Status.UNKNOWN, data)

    }
}