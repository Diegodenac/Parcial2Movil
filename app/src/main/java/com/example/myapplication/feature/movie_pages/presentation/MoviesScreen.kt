package com.example.myapplication.feature.movie_pages.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen( modifier: Modifier,
                  vm: MoviesViewModel = koinViewModel()
){
    val state by vm.state.collectAsState()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        vm.getMovies()
    }

    when(val st = state){
        is MoviesViewModel.MoviesStateUI.Success ->{
            val movies = st.movies
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movies.size){
                    index->
                    val movie = movies[index]
                    MovieItem(movie=movie)
                }
            }
        }
        is MoviesViewModel.MoviesStateUI.Loading ->{
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        is MoviesViewModel.MoviesStateUI.Error ->{
            Text(
                text = st.message,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.error
            )
        }
        MoviesViewModel.MoviesStateUI.Init -> {
            Text("Init")
        }
    }
}

@Composable
fun MovieItem(movie: MovieModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            // Imagen del poster usando Coil
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w185/${movie.posterPath}"),
                contentDescription = movie.title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}