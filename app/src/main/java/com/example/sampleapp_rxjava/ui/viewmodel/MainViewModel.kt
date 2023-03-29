package com.example.sampleapp_rxjava.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp_rxjava.data.model.Movies
import com.example.sampleapp_rxjava.data.model.MoviesItem
import com.example.sampleapp_rxjava.data.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _movieList = MutableLiveData<Movies>()
    val movieList: LiveData<Movies>
        get() = _movieList

    private val _selectedMovie = MutableLiveData<MoviesItem>()
    val selectedMovie: LiveData<MoviesItem>
        get() = _selectedMovie

    private lateinit var disposable: Disposable

    private val observer: Observer<Movies> = object : Observer<Movies> {
        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(t: Movies) {
            _movieList.postValue(t)
        }

        override fun onError(e: Throwable) {
            _movieList.postValue(null)
        }

        override fun onComplete() {
        }

    }

    fun selectMovie(moviesItem: MoviesItem) {
        _selectedMovie.postValue(moviesItem)
    }

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun clearDisposable() {
        disposable.dispose()
    }
}