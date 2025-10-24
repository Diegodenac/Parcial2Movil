package com.example.myapplication.feature.movie_pages.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier,
    vm: MoviesViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.getMovies()
    }

    when {
        state.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        state.error != null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            MoviesContent(
                modifier = modifier,
                watchLaterMovies = state.watchLaterMovies,
                allMovies = state.allMovies,
                watchLaterTitles = state.watchLaterTitles,
                onAddToWatchLater = { movie -> vm.addToWatchLater(movie) },
                onRemoveFromWatchLater = { title -> vm.removeFromWatchLater(title) }
            )
        }
    }
}

@Composable
fun MoviesContent(
    modifier: Modifier,
    watchLaterMovies: List<MovieModel>,
    allMovies: List<MovieModel>,
    watchLaterTitles: Set<String>,
    onAddToWatchLater: (MovieModel) -> Unit,
    onRemoveFromWatchLater: (String) -> Unit
) {
    // Filtrar películas que NO están en "Ver después"
    val otherMovies = allMovies.filter { movie ->
        !watchLaterTitles.contains(movie.title)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Sección "Ver después"
        if (watchLaterMovies.isNotEmpty()) {
            item {
                SectionHeader(title = "Ver después")
            }

            items(watchLaterMovies.size) { index ->
                val movie = watchLaterMovies[index]
                MovieItem(
                    movie = movie,
                    isInWatchLater = true,
                    onAddToWatchLater = { onAddToWatchLater(movie) },
                    onRemoveFromWatchLater = { onRemoveFromWatchLater(movie.title) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Sección "Todas las películas"
        if (otherMovies.isNotEmpty()) {
            item {
                SectionHeader(title = "Todas las películas")
            }

            items(otherMovies.size) { index ->
                val movie = otherMovies[index]
                MovieItem(
                    movie = movie,
                    isInWatchLater = false,
                    onAddToWatchLater = { onAddToWatchLater(movie) },
                    onRemoveFromWatchLater = { onRemoveFromWatchLater(movie.title) }
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun MovieItem(
    movie: MovieModel,
    isInWatchLater: Boolean,
    onAddToWatchLater: () -> Unit,
    onRemoveFromWatchLater: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del poster
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w185/${movie.posterPath}"),
                contentDescription = movie.title,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Título
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Botón de acción
            if (isInWatchLater) {
                // Botón para eliminar de "Ver después"
                IconButton(
                    onClick = onRemoveFromWatchLater,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Eliminar de ver después"
                    )
                }
            } else {
                // Botón para agregar a "Ver después"
                IconButton(
                    onClick = onAddToWatchLater,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar a ver después"
                    )
                }
            }
        }
    }
}