package com.death.photonik.data.remote

import com.death.photonik.data.model.AddPhotoRequest
import com.death.photonik.data.model.AddPhotoResponse
import com.death.photonik.data.model.Photos
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import javax.inject.Singleton

@Singleton
interface NetworkService{

    @GET(Endpoints.PHOTO)
    fun getAllPhotos():Single<Photos>

    @PUT(Endpoints.PHOTO)
    fun uploadPhoto(@Body request: AddPhotoRequest):Single<AddPhotoResponse>

}