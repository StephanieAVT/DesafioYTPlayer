package com.st.auth.presentation

sealed class AuthViewState {
    data object Idle : AuthViewState()
    data object Loading : AuthViewState()
    data class Success(val userName: String) : AuthViewState()
    data class Error(val message: String) : AuthViewState()
}