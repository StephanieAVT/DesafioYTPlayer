package com.st.desafioyoutubeplayer

import android.app.Application

import com.st.auth.di.authModule
import com.st.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YoutubeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@YoutubeApplication)
            modules( authModule, homeModule)
        }

    }
}