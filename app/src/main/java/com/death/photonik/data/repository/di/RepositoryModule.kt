package com.death.photonik.data.repository.di

import com.death.photonik.data.repository.PhotoRepository
import com.death.photonik.data.repository.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule{

    @Provides
    fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl) : PhotoRepository
            = photoRepositoryImpl

}