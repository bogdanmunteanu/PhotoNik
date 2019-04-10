package com.death.photonik.data.model
import com.google.gson.annotations.SerializedName


data class AddPhotoRequest(
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)