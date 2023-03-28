package com.example.sampleapp_rxjava.di

import com.example.sampleapp_rxjava.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}