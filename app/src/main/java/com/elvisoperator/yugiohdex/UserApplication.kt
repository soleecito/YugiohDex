package com.elvisoperator.yugiohdex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


class UserApplication : Application() {

    companion object {
        lateinit var  prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}