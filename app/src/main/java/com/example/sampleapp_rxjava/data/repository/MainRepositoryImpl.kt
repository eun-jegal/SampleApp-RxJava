package com.example.sampleapp_rxjava.data.repository

import com.example.sampleapp_rxjava.data.api.APIService
import com.example.sampleapp_rxjava.data.model.Movies
import io.reactivex.rxjava3.core.Observable

class MainRepositoryImp(
    private val apiService: APIService
) : MainRepository {
    override fun getAllMovies(): Observable<Movies> {
        return apiService.getAllMovies()
    }
}