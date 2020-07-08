package co.effectstudios.photox.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoData(
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
): Parcelable