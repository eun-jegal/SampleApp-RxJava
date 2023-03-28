package com.example.sampleapp_rxjava.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        (activity as MainActivity).mainViewModel.selectedMovie.observe(viewLifecycleOwner, Observer {
            binding.apply {
                title.text = it.title
                released.text = it.released
                director.text = it.director
                genre.text = it.genre
                casts.text = it.actors
                rating.text = it.imdbRating
                plot.text = it.plot
            }
        })
    }
}