package com.shashank.remote.model.response.top_anime


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopAnimeResponse(
    @Json(name = "data")
    val `data`: List<DataResponse>?
)


@JsonClass(generateAdapter = true)
data class DataResponse(
    @Json(name = "episodes")
    val episodes: Int?,
    @Json(name = "images")
    val images: ImagesResponse?,
    @Json(name = "mal_id")
    val malId: Int?,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "rating")
    val rating: String?,
    @Json(name = "score")
    val score: Double?,
    @Json(name = "season")
    val season: String?,
    @Json(name = "title")
    val title: String?
)

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = "jpg") var jpg: JpgResponse?
)

@JsonClass(generateAdapter = true)
data class JpgResponse(
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "small_image_url") val smallImageUrl: String?,
    @Json(name = "large_image_url") val largeImageUrl: String?
)