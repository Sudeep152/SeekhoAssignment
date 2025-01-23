package com.shashank.data.mapper

import com.shashank.domain.domainModel.response.top_anime.Data
import com.shashank.domain.domainModel.response.top_anime.Images
import com.shashank.domain.domainModel.response.top_anime.Jpg
import com.shashank.domain.domainModel.response.top_anime.TopAnime
import com.shashank.remote.model.response.top_anime.DataResponse
import com.shashank.remote.model.response.top_anime.ImagesResponse
import com.shashank.remote.model.response.top_anime.JpgResponse
import com.shashank.remote.model.response.top_anime.TopAnimeResponse

fun TopAnimeResponse.toDomain(): TopAnime {
    return TopAnime(
        data = this.data?.map { it.toDomain() } ?: emptyList()
    )
}

fun DataResponse.toDomain(): Data {
    return Data(
        episodes = this.episodes ?: 0,
        images = this.images?.toDomain() ?: Images(Jpg("", "", "")),
        malId = this.malId ?: 0,
        rank = this.rank ?: 0,
        rating = this.rating ?: "",
        score = this.score ?: 0.0,
        season = this.season ?: "",
        title = this.title ?: "Unknown Title",
    )
}

fun ImagesResponse.toDomain(): Images {
    return Images(
        jpg = this.jpg?.toDomain() ?: Jpg("", "", "")
    )
}

fun JpgResponse.toDomain(): Jpg {
    return Jpg(
        imageUrl = this.imageUrl ?: "",
        smallImageUrl = this.smallImageUrl ?: "",
        largeImageUrl = this.largeImageUrl ?: ""
    )
}
        
        