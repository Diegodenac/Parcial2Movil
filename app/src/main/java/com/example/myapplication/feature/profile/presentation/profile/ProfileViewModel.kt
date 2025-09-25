package com.example.myapplication.feature.profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.github.presentation.github.GithubViewModel.GitHubStateUI
import com.example.myapplication.feature.profile.domain.model.UserProfile
import com.example.myapplication.feature.profile.domain.usercases.GetProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    val useCase: GetProfileUseCase
): ViewModel() {
    sealed class ProfileStateUI{
        class Init(val userProfile: UserProfile): ProfileStateUI()
        object Loading: ProfileStateUI()
        class Error(val message: String): ProfileStateUI()
    }

    private val _state= MutableStateFlow<ProfileStateUI>(ProfileStateUI.Loading)
    val state: StateFlow<ProfileStateUI> = _state.asStateFlow()

    fun loadUserProfile(){
        viewModelScope.launch (Dispatchers.IO){
            _state.value = ProfileStateUI.Loading
            val result = useCase.invoke()

            result.fold(
                onSuccess = {
                        user -> _state.value = ProfileStateUI.Init(user)
                },
                onFailure = {
                        error -> _state.value = ProfileStateUI.Error(message = error.message ?: "Error al recuperar el perfil")
                }
            )
        }
    }
}