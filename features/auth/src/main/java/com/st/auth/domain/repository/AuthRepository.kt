package com.st.auth.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<FirebaseUser>
}
