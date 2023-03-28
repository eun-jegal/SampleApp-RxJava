package com.example.sampleapp_rxjava.di

import com.example.sampleapp_rxjava.data.api.APIService
import com.example.sampleapp_rxjava.data.repository.MainRepository
import com.example.sampleapp_rxjava.data.repository.MainRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: APIService): MainRepository {
        return MainRepositoryImp(apiService)
    }
}