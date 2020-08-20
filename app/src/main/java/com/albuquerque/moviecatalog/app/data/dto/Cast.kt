package com.albuquerque.moviecatalog.app.data.dto

import com.albuquerque.moviecatalog.app.remote.Config
import com.google.gson.annotations.SerializedName

data class Cast(
        val id: String,
        val character: String,
        val gender: String? = null,
        val order: Int,
        val name: String,
        @SerializedName("profile_path") val profilePath: String? = null
) {
    val profilePicture: String
        get() = Config.BASE_IMAGE_URL.plus(profilePath)
}