package com.example.sampleapp_rxjava

import android.app.Application
import com.example.sampleapp_rxjava.di.AppComponent
import com.example.sampleapp_rxjava.di.DaggerAppComponent

class SampleApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}