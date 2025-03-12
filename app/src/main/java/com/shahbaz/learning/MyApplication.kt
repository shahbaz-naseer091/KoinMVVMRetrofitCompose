package com.shahbaz.learning

import android.app.Application
import com.shahbaz.learning.di.networkModule
import com.shahbaz.learning.di.repositoryModule
import com.shahbaz.learning.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(networkModule, repositoryModule,viewModelModule)
        }
    }
}