package com.shashank.remote.model.response.anime_detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeDetailsResponse(
    @Json(name = "data")
    val `data`: DataResponse?
)
@JsonClass(generateAdapter = true)
data class DataResponse(
    @Json(name = "trailer")
    val trailer: TrailerResponse?, // For trailer URL
    @Json(name = "images")
    val images: ImagesResponse?, // For poster image
    @Json(name = "title")
    val title: String?, // Title
    @Json(name = "synopsis")
    val synopsis: String?, // Plot/Synopsis
    @Json(name = "genres")
    val genres: List<GenreResponse>?, // Genre(s)
    @Json(name = "episodes")
    val episodes: Int?, // Number of Episodes
    @Json(name = "rating")
    val rating: String? // Rating
)


@JsonClass(generateAdapter = true)
data class GenreResponse(
    @Json(name = "mal_id")
    val malId: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)


@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = "jpg")
    val jpg: JpgResponse?,
)

@JsonClass(generateAdapter = true)
data class JpgResponse(
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "large_image_url")
    val largeImageUrl: String?,
    @Json(name = "small_image_url")
    val smallImageUrl: String?
)

//@JsonClass(generateAdapter = true)
//data class Title(
//    @Json(name = "title")
//    val title: String?,
//    @Json(name = "type")
//    val type: String?
//)

@JsonClass(generateAdapter = true)
data class TrailerResponse(
    @Json(name = "embed_url")
    val embedUrl: String?,
    @Json(name = "images")
    val images: JpgResponse?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "youtube_id")
    val youtubeId: String?
)

