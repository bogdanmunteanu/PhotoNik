package com.death.photonik.data.remote.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkCacheSize

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkCacheDirectory