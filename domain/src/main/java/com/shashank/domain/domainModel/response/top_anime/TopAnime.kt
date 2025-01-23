package com.shashank.domain.domainModel.response.top_anime


data class TopAnime(
    val `data`: List<Data>,
)

data class Data(
    val episodes: Int?,
    val images: Images?,
    val malId: Int?,
    val rank: Int?,
    val rating: String?,
    val score: Double?,
    val season: String?,
    val title: String?,
)

data class Images(
    val jpg: Jpg?
)

data class Jpg(
    val imageUrl: String?,
    val smallImageUrl: String?,
    val largeImageUrl: String?
)