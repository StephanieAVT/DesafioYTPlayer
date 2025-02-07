package com.st.auth.presentation

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

sealed class AuthViewAction {
    data class LoginWithGoogle(val account: GoogleSignInAccount) : AuthViewAction()
    data object NavigateToHome : AuthViewAction()
}
