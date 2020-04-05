package com.albuquerque.moviecatalog.app.data.dto

import com.google.gson.annotations.SerializedName

data class Cast(
        val id: String,
        val character: String,
        val gender: String? = null,
        val order: String,
        val name: String,
        @SerializedName("profile_path") val profilePath: String? = null
)