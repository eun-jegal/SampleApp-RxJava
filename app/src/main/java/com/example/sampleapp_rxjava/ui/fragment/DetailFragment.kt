package com.example.sampleapp_rxjava.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleapp_rxjava.databinding.FragmentDetailBinding
import com.example.sampleapp_rxjava.ui.MainActivity

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selectedMovie = (activity as MainActivity).mainViewModel.selectedMovie.value
        selectedMovie?.let {
            binding.apply {
                titleAndYear.text = it.title
                director.text = it.director
                casts.text = it.actors
                plot.text = it.plot
            }
        }
    }
}