package com.example.sampleapp_rxjava.data.api

import com.example.sampleapp_rxjava.data.model.Movies
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface APIService {
    @GET("Film.JSON")
    fun getAllMovies(): Observable<Movies>
}