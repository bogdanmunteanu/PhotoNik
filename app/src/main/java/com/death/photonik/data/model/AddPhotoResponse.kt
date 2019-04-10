package com.death.photonik.data.model
import com.google.gson.annotations.SerializedName


data class AddPhotoResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)