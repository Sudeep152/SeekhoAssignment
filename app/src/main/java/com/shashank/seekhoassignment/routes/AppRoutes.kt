package com.shashank.seekhoassignment.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoutes {

    @Serializable
    data object TopAnimeListScreen : AppRoutes()

    @Serializable
    data class AnimeDetailsScreen(val id: Int) : AppRoutes()

}