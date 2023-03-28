package com.example.sampleapp_rxjava.data.repository

import com.example.sampleapp_rxjava.data.model.Movies
import io.reactivex.rxjava3.core.Observable

interface MainRepository {
    fun getAllMovies(): Observable<Movies>
}