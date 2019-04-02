package com.death.photonik.data.model
import com.google.gson.annotations.SerializedName


data class Photos(
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("status")
    val status: Int
)

data class Photo(
    @SerializedName("_id")
    val id: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)