package com.shashank.data.mapper

import com.shashank.domain.domainModel.response.anime_detail.AnimeDetails
import com.shashank.domain.domainModel.response.anime_detail.Data
import com.shashank.domain.domainModel.response.anime_detail.Genre
import com.shashank.domain.domainModel.response.anime_detail.Images
import com.shashank.domain.domainModel.response.anime_detail.Jpg
import com.shashank.domain.domainModel.response.anime_detail.Trailer
import com.shashank.remote.model.response.anime_detail.AnimeDetailsResponse
import com.shashank.remote.model.response.anime_detail.DataResponse
import com.shashank.remote.model.response.anime_detail.GenreResponse
import com.shashank.remote.model.response.anime_detail.ImagesResponse
import com.shashank.remote.model.response.anime_detail.JpgResponse
import com.shashank.remote.model.response.anime_detail.TrailerResponse


fun AnimeDetailsResponse.toDomain(): AnimeDetails {
    return AnimeDetails(
        data = this.data?.toDomain()
    )
}

fun DataResponse.toDomain(): Data {
    return Data(
        trailer = this.trailer?.toDomain(),
        images = this.images?.toDomain(),
        title = this.title ?: "Unknown Title",
        synopsis = this.synopsis ?: "No Synopsis Available",
        genres = this.genres?.map { it.toDomain() } ?: emptyList(),
        episodes = this.episodes ?: 0,
        rating = this.rating ?: "N/A"
    )
}

fun ImagesResponse.toDomain(): Images {
    return Images(
        jpg = this.jpg?.toDomain()
    )
}

fun JpgResponse.toDomain(): Jpg {
    return Jpg(
        imageUrl = this.imageUrl ?: "",
        largeImageUrl = this.largeImageUrl ?: "",
        smallImageUrl = this.smallImageUrl ?: ""
    )
}

fun TrailerResponse.toDomain(): Trailer {
    return Trailer(
        embedUrl = this.embedUrl ?: "",
        images = this.images?.toDomain(),
        url = this.url ?: "",
        youtubeId = this.youtubeId ?: ""
    )
}

fun GenreResponse.toDomain(): Genre {
    return Genre(
        malId = this.malId ?: 0,
        name = this.name ?: "",
        type = this.type ?: "",
        url = this.url ?: ""
    )
}