package com.example.myapplication.feature.github.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.github.domain.model.UserModel
import com.example.myapplication.feature.github.domain.usercases.FindByNicknameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GithubViewModel(
    val useCase: FindByNicknameUseCase
): ViewModel() {
    sealed class GitHubStateUI{
        object Init: GitHubStateUI()
        object Loading: GitHubStateUI()
        class Error(val message: String): GitHubStateUI()
        class Success(val github: UserModel): GitHubStateUI()
    }

    private val _state = MutableStateFlow<GitHubStateUI>(GitHubStateUI.Init) //es privado para que no se pueda acceder por fuera de la clase
    val state: StateFlow<GitHubStateUI> = _state.asStateFlow()

    fun fetchAlias(nickname: String){
        viewModelScope.launch (Dispatchers.IO) {
            _state.value = GitHubStateUI.Loading
            val result = useCase.invoke(nickname)

            result.fold(
                onSuccess = {
                    user -> _state.value = GitHubStateUI.Success(user)
                },
                onFailure = {
                    error -> _state.value = GitHubStateUI.Error(message = error.message ?: "Error desconocido")
                }
            )
        }
    }
}