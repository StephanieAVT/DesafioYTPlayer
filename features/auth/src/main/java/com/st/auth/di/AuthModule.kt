package com.st.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.st.auth.data.AuthRepositoryImpl
import com.st.auth.domain.repository.AuthRepository
import com.st.auth.presentation.AuthViewModel
import org.koin.dsl.module

val authModule = module {
    single { FirebaseAuth.getInstance() }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory { AuthViewModel(get()) }
}

