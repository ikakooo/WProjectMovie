package com.example.movieapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.constants.Constants
import com.example.movieapp.fragments.MovieListFragmentDirections
import com.example.movieapp.network.Result
import kotlinx.android.synthetic.main.movie_layout.view.*

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var movies = emptyList<Result>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.titleTV.text = movie.original_title
        holder.itemView.descriptionTV.text = movie.overview
        Glide.with(holder.itemView.context).load(Constants.BASE_IMG_URL+movie.poster_path).into(holder.itemView.poster)
        holder.itemView.setOnClickListener {
//            deleteListener.deleteItem(position)
        }

        holder.itemView.editButton.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToEditMovie(movie)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(list: List<Result>){
        movies = list
        notifyDataSetChanged()
    }
}