package com.example.sampleapp_rxjava.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp_rxjava.data.model.MoviesItem
import com.example.sampleapp_rxjava.databinding.FragmentHomeBinding
import com.example.sampleapp_rxjava.ui.MainActivity
import com.example.sampleapp_rxjava.ui.MoviesAdapter

class HomeFragment : Fragment() {

    private val moviesAdapter = MoviesAdapter()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        requestMovieData()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = moviesAdapter
            moviesAdapter.setOnItemClickListener {
                (activity as MainActivity).navigateToDetailFragment(it)
            }
        }
    }

    private fun requestMovieData() {
        (activity as MainActivity).mainViewModel.run {
            getAllMovies()
            movieList.observe(
                activity as MainActivity,
                Observer {
                    if (it.isNullOrEmpty()) {
                        hideProgressbar()
                        showErrorMessage()
                    } else {
                        hideProgressbar()
                        updateList(it)
                    }
                })
        }
    }

    private fun hideProgressbar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorMessage() {
        binding.textView.apply {
            visibility = View.VISIBLE
            text = "Something is wrong"
        }
    }

    private fun updateList(list: List<MoviesItem>) {
        moviesAdapter.updateList(list)
        binding.recyclerView.visibility = View.VISIBLE
    }
}