package com.mo.aad

import android.app.Application
import com.mo.aad.koin.remoteModule
import com.mo.aad.koin.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(uiModule, remoteModule))
        }
    }
}