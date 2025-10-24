package com.example.myapplication.feature.movie_pages.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.usecases.AddToWatchLaterUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.GetMoviesUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.GetWatchLaterMoviesUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.IsInWatchLaterUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.RemoveFromWatchLaterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getWatchLaterMoviesUseCase: GetWatchLaterMoviesUseCase,
    private val addToWatchLaterUseCase: AddToWatchLaterUseCase,
    private val removeFromWatchLaterUseCase: RemoveFromWatchLaterUseCase,
    private val isInWatchLaterUseCase: IsInWatchLaterUseCase
): ViewModel() {

    data class MoviesUIState(
        val isLoading: Boolean = false,
        val allMovies: List<MovieModel> = emptyList(),
        val watchLaterMovies: List<MovieModel> = emptyList(),
        val watchLaterTitles: Set<String> = emptySet(),
        val error: String? = null
    )

    private val _state = MutableStateFlow(MoviesUIState())
    val state: StateFlow<MoviesUIState> = _state.asStateFlow()

    init {
        observeWatchLaterMovies()
    }

    // Observar cambios en "Ver después" en tiempo real
    private fun observeWatchLaterMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getWatchLaterMoviesUseCase.invoke().collect { watchLaterMovies ->
                _state.value = _state.value.copy(
                    watchLaterMovies = watchLaterMovies,
                    watchLaterTitles = watchLaterMovies.map { it.title }.toSet()
                )
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val result = getMoviesUseCase.invoke()

            result.fold(
                onSuccess = { movies ->
                    _state.value = _state.value.copy(
                        allMovies = movies,
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message ?: "Error desconocido"
                    )
                }
            )
        }
    }

    fun addToWatchLater(movie: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addToWatchLaterUseCase.invoke(movie)
        }
    }

    fun removeFromWatchLater(movieTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromWatchLaterUseCase.invoke(movieTitle)
        }
    }

    fun isInWatchLater(movieTitle: String): Boolean {
        return _state.value.watchLaterTitles.contains(movieTitle)
    }
}