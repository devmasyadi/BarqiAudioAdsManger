package com.ahmadsuyadi.libbarqi

import android.app.Application
import com.ahmadsuyadi.barqiadsmanager.di.barqiAdsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(barqiAdsModule)
        }
    }

}