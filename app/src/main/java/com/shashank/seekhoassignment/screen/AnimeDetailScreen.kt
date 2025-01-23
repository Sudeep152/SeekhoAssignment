package com.shashank.seekhoassignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.shashank.domain.domainModel.response.anime_detail.Data
import com.shashank.domain.domainModel.response.anime_detail.Genre
import com.shashank.seekhoassignment.viewmodel.AnimeDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailsScreen(modifier: Modifier = Modifier, id: Int, onBackPress: () -> Unit) {
    val viewModel: AnimeDetailsViewModel = hiltViewModel()
    val animeDetails by viewModel.animeDetail.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    LaunchedEffect(key1 = id) {
        if (animeDetails?.data == null) {
            viewModel.getAnimeDetail(id)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Details") },
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            AnimeDetailContent(
                modifier = Modifier.padding(paddingValues),
                anime = animeDetails?.data
            )
        }
    }
}

@Composable
fun AnimeDetailContent(modifier: Modifier = Modifier, anime: Data?) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Trailer or Poster
        if (anime?.trailer != null) {
            YoutubeScreen(videoId = anime.trailer!!.youtubeId.toString())
        } else {
            PlaceholderPoster(posterUrl = anime?.images?.jpg?.imageUrl.toString())
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title and Synopsis
        anime?.let {
            AnimeDetailsTitleSection(
                title = it.title,
                synopsis = it.synopsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Genres
            AnimeGenres(genres = it.genres)

            Spacer(modifier = Modifier.height(16.dp))

            // Episodes and Rating
            AnimeEpisodesAndRating(episodes = it.episodes, rating = it.rating)
        }
    }
}

@Composable
fun AnimeDetailsTitleSection(title: String?, synopsis: String?) {
    Column {
        Text(
            text = title ?: "Unknown Title",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Synopsis: ${synopsis ?: "Not Available"}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeGenres(genres: List<Genre>?) {
    if (!genres.isNullOrEmpty()) {
        Text(
            text = "Genres",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                DefaultItemUI(text = genre.name?:"")
            }
        }
    }
}

@Composable
fun AnimeEpisodesAndRating(episodes: Int?, rating: String?) {
    Column {
        Text(
            text = "Episodes: ${episodes ?: "Unknown"}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${rating ?: "N/A"}/10",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun PlaceholderPoster(modifier: Modifier = Modifier, posterUrl: String) {
    AsyncImage(
        model = posterUrl,
        contentDescription = "Poster",
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun YoutubeScreen(videoId: String) {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

@Composable
fun DefaultItemUI(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = MaterialTheme.typography.bodySmall)
    }
}