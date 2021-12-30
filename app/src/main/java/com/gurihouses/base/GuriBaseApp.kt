package com.gurihouses.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class GuriBaseApp:Application() {


    companion object {
        lateinit var instance: GuriBaseApp
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


    }
}