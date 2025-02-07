package com.st.auth.presentation

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.activity.result.IntentSenderRequest

class AuthViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val googleAuthUiClient = GoogleAuthUiClient(
        context = application.applicationContext,
        oneTapClient = Identity.getSignInClient(application.applicationContext)
    )

    private val _viewState = MutableStateFlow<AuthViewState>(AuthViewState.Idle)
    val viewState: StateFlow<AuthViewState> = _viewState

    private val _viewAction = MutableSharedFlow<AuthViewAction>()
    val viewAction: SharedFlow<AuthViewAction> = _viewAction

    suspend fun startGoogleSignIn(): IntentSenderRequest? {
        return googleAuthUiClient.signIn()?.let { IntentSenderRequest.Builder(it).build() }
    }

    fun handleGoogleSignInIntent(intent: Intent?) {
        viewModelScope.launch {
            try {
                if (intent == null) {
                    _viewState.value = AuthViewState.Error("Login cancelado pelo usuário.")
                    return@launch
                }

                val signInResult = googleAuthUiClient.signInWithIntent(intent)
                if (signInResult.data != null) {
                    _viewState.value = AuthViewState.Success(
                        userName = signInResult.data.username ?: "Usuário",
                    )
                    _viewAction.emit(AuthViewAction.NavigateToHome)
                } else {
                    _viewState.value = AuthViewState.Error(signInResult.errorMessage ?: "Erro desconhecido ao autenticar")
                }
            } catch (e: ApiException) {
                _viewState.value = AuthViewState.Error("Erro ao processar login: ${e.localizedMessage}")
            } catch (e: Exception) {
                _viewState.value = AuthViewState.Error("Erro inesperado: ${e.localizedMessage}")
            }
        }
    }

}
