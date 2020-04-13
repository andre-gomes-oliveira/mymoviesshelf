package com.andregomesolievira.mymoviesshelf

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andregomesolievira.mymoviesshelf.database.MovieEntity
import com.andregomesolievira.mymoviesshelf.network.SimpleMovieParcel
import com.andregomesolievira.mymoviesshelf.overview.OMDbApiStatus
import com.andregomesolievira.mymoviesshelf.overview.PosterGridAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * When there is no MovieEntity property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SimpleMovieParcel>?) {
    val adapter = recyclerView.adapter as PosterGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("listEntities")
fun bindMovieEntities(recyclerView: RecyclerView, data: List<MovieEntity>?) {
    data?.let {
        val movies: MutableList<SimpleMovieParcel> = mutableListOf()
        data.forEach {
            val movie = SimpleMovieParcel(it.title, it.imdbID, it.poster)
            movies.add(movie)
        }

        bindRecyclerView(recyclerView, movies)
    }
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("posterUrl")
fun bindImage(imgView: ImageView, posterUrl: String?) {
    posterUrl?.let {
        val imgUri = posterUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

/**
 * This binding adapter displays the [OMDbApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("omdbApiStatus")
fun bindStatus(statusImageView: ImageView, status: OMDbApiStatus?) {
    when (status) {
        OMDbApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        OMDbApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        OMDbApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}