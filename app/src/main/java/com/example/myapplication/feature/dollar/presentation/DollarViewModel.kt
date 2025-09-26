package com.example.myapplication.feature.dollar.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.dollar.domain.model.DollarModel
import com.example.myapplication.feature.dollar.domain.usecase.FetchDollarUseCase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DollarViewModel(
    private val fetchDollarUseCase: FetchDollarUseCase
) : ViewModel() {

    sealed class DollarUIState {
        object Loading : DollarUIState()
        class Error(val message: String) : DollarUIState()
        class Success(val dataList: List<DollarModel>) : DollarUIState()
    }

    private val _state = MutableStateFlow<DollarUIState>(DollarUIState.Loading)
    val state: StateFlow<DollarUIState> = _state.asStateFlow()

    init {
        observeDollarUpdates()
    }

    private fun observeDollarUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getToken()
                // Escucha Firebase y cada vez que llega un cambio se guarda en Room
                fetchDollarUseCase.invoke().collect {
                    // Después de insertar, recarga la lista completa desde Room
                    loadAllFromLocal()
                }
            } catch (e: Exception) {
                _state.value = DollarUIState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    private fun loadAllFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = fetchDollarUseCase.getAllFromLocal()
                _state.value = DollarUIState.Success(list)
            } catch (e: Exception) {
                _state.value = DollarUIState.Error(e.message ?: "Error cargando histórico")
            }
        }
    }

    suspend fun getToken(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FIREBASE", "getInstanceId failed")
                    continuation.resumeWithException(
                        task.exception ?: Exception("Unknown error")
                    )
                    return@addOnCompleteListener
                }
                val token = task.result
                Log.d("FIREBASE", "FCM Token: $token")
                continuation.resume(token ?: "")
            }
    }
}
