package com.death.photonik.data.repository

import com.death.photonik.data.model.Photos
import io.reactivex.Single

interface PhotoRepository{
    fun getPhoto(): Single<Photos>
}