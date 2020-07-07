package co.effectstudios.photox.network

import com.squareup.moshi.Json

class PhotoData(
    @Json(name = "id")
    val id: Int,
    @Json(name = "author")
    val author: String,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "download_url")
    val downloadUrl: String
)