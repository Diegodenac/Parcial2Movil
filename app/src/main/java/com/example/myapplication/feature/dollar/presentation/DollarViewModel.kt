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
    val fetchDollarUseCase: FetchDollarUseCase
): ViewModel() {
    sealed class DollarUIState{
        object Loading: DollarUIState()
        class Error(val message: String): DollarUIState()
        class Success(val data: DollarModel): DollarUIState()
    }

    init {
        getDollar()
    }

    private val _state = MutableStateFlow<DollarUIState>(DollarUIState.Loading)
    val state: StateFlow<DollarUIState> = _state.asStateFlow()

    fun getDollar(){
        viewModelScope.launch(Dispatchers.IO) {
            getToken()
            fetchDollarUseCase.invoke().collect{
                data -> _state.value = DollarUIState.Success(data)
            }
        }
    }

    suspend fun getToken(): String = suspendCoroutine { continuation -> FirebaseMessaging.getInstance().token
        .addOnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.w("FIREBASE", "getInstanceId failed")
                continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d("FIREBASE", "FCM Token: $token")
            continuation.resume(token?:"")
        }
    }
}