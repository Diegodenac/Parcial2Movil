package com.example.myapplication.feature.movie_pages.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {
    sealed class MoviesStateUI{
        object Init: MoviesStateUI()
        object Loading: MoviesStateUI()
        class Success(val movies: List<MovieModel>): MoviesStateUI()
        class Error(val message: String): MoviesStateUI()
    }

    private val _state = MutableStateFlow<MoviesStateUI>(MoviesStateUI.Init)
    val state: StateFlow<MoviesStateUI> = _state.asStateFlow()

    fun getMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MoviesStateUI.Loading
            val result = getMoviesUseCase.invoke()

            result.fold(
                onSuccess = {
                    movies -> _state.value = MoviesStateUI.Success(movies)
                },
                onFailure = {
                    error -> _state.value= MoviesStateUI.Error(
                        message = error.message ?:"Error desconocido"
                    )
                }
            )
        }
    }
}