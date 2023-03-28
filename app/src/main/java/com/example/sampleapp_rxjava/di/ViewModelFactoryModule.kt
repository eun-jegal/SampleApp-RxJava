package com.example.sampleapp_rxjava.di

import com.example.sampleapp_rxjava.data.repository.MainRepository
import com.example.sampleapp_rxjava.ui.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Singleton
    @Provides
    fun provideViewModelFactory(repository: MainRepository): MainViewModelFactory {
        return MainViewModelFactory(repository)
    }
}