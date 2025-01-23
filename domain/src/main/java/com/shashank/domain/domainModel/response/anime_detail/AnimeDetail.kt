package com.shashank.domain.domainModel.response.anime_detail

data class AnimeDetails(
    val data: Data?
)

data class Data(
    val trailer: Trailer?,
    val images: Images?,
    val title: String?,
    val synopsis: String?,
    val genres: List<Genre>?,
    val episodes: Int?,
    val rating: String?
)

data class Genre(
    val malId: Int?,
    val name: String?,
    val type: String?,
    val url: String?
)

data class Images(
    val jpg: Jpg?
)

data class Jpg(
    val imageUrl: String?,
    val largeImageUrl: String?,
    val smallImageUrl: String?
)

data class Trailer(
    val embedUrl: String?,
    val images: Jpg?,
    val url: String?,
    val youtubeId: String?
)