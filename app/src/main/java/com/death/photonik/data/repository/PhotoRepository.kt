package com.death.photonik.data.repository

import com.death.photonik.data.model.AddPhotoRequest
import com.death.photonik.data.model.AddPhotoResponse
import com.death.photonik.data.model.Photos
import io.reactivex.Single

interface PhotoRepository{
    fun getPhoto(): Single<Photos>
    fun uploadPhoto(request:AddPhotoRequest): Single<AddPhotoResponse>
}