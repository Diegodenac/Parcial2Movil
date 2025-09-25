package com.example.myapplication.feature.loginRecuperatorio.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.loginRecuperatorio.domain.model.ChatModel
import com.example.myapplication.feature.loginRecuperatorio.domain.model.UserLoginModel
import com.example.myapplication.feature.loginRecuperatorio.domain.usercases.AuthenticateLogInUseCase
import com.example.myapplication.feature.loginRecuperatorio.domain.usercases.GoToSupportUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginRecuperatorioViewModel(
    val useCaseAuthUser: AuthenticateLogInUseCase,
    val useCaseGoToChat: GoToSupportUseCase
): ViewModel() {
    sealed class LoginStateUI{
        object Init: LoginStateUI()
        object Loading: LoginStateUI()
        class AccessSuccess(val userLogged: UserLoginModel): LoginStateUI()
        class AccessDenied(val message:String): LoginStateUI()
        object MissingParameters: LoginStateUI()
        class SupportSuccess(val support: ChatModel): LoginStateUI()
        class ErrorSupport(val message:String): LoginStateUI()
    }

    private val _state= MutableStateFlow<LoginStateUI>(LoginStateUI.Init)
    val state: StateFlow<LoginStateUI> = _state.asStateFlow()

    fun authUser(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = LoginStateUI.Loading
            val result = useCaseAuthUser.invoke(email, password)
            result.fold(
                onSuccess = {
                    user -> _state.value = LoginStateUI.AccessSuccess(user)
                },
                onFailure = {
                    error -> _state.value = LoginStateUI.AccessDenied(error.message ?: "Error desconocido")
                }
            )
        }
    }

    fun goToSupport(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = LoginStateUI.Loading
        }
    }
}