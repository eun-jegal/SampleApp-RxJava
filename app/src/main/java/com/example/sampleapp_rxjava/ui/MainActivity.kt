package com.example.sampleapp_rxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp_rxjava.R
import com.example.sampleapp_rxjava.SampleApp
import com.example.sampleapp_rxjava.data.model.MoviesItem
import com.example.sampleapp_rxjava.ui.fragment.DetailFragment
import com.example.sampleapp_rxjava.ui.fragment.HomeFragment
import com.example.sampleapp_rxjava.ui.viewmodel.MainViewModel
import com.example.sampleapp_rxjava.ui.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as SampleApp).appComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        initFragments()
    }

    private fun initFragments() {
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, homeFragment).commit()
    }

    fun navigateToDetailFragment(moviesItem: MoviesItem) {
        mainViewModel.selectMovie(moviesItem)
        val detailFragment = DetailFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, detailFragment).addToBackStack(null).commit()
    }
}