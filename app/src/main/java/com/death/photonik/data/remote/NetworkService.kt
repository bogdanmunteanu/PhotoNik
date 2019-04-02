package com.death.photonik.data.remote

import com.death.photonik.data.model.Photos
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService{

    @GET(Endpoints.PHOTO)
    fun getAllPhotos():Single<Photos>

}