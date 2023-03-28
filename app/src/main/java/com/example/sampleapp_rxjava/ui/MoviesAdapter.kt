package com.example.sampleapp_rxjava.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp_rxjava.data.model.MoviesItem
import com.example.sampleapp_rxjava.databinding.ListItemBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.CustomViewHolder>() {

    private val movieList = mutableListOf<MoviesItem>()
    private var onItemClickListener: ((MoviesItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (MoviesItem) -> Unit) {
        onItemClickListener = listener
    }

    fun updateList(list: List<MoviesItem>) {
        movieList.run {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class CustomViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MoviesItem) {
            binding.apply {
                title.text = movie.title
                time.text = movie.released
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(movie)
                    }
                }
            }
        }
    }
}