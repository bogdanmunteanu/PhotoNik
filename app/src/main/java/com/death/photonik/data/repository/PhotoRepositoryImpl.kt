package com.death.photonik.data.repository

import com.death.photonik.data.model.Photos
import com.death.photonik.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : PhotoRepository{

    override fun getPhoto(): Single<Photos> =
        networkService.getAllPhotos()

}