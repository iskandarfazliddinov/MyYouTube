package com.example.myyoutube.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SnippetYt(
    @SerializedName("title")
    val title :String,

    @SerializedName("description")
    val description:String,

    @SerializedName("customUrl")
    val customUrl :String,

    @SerializedName("publishedAt")
    val publishedAt:String,

    @SerializedName("thumbnails")
    val thumbnails:ThumbnailsYt,

    @SerializedName("country")
    val country:String

)