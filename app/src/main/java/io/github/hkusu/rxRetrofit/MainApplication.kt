package io.github.hkusu.rxRetrofit

import android.app.Application

import com.rohitss.uceh.UCEHandler

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        UCEHandler.Builder(this).build()
    }


}
