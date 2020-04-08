package com.andregomesolievira.mymoviesshelf.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andregomesolievira.mymoviesshelf.databinding.GridViewItemBinding
import com.andregomesolievira.mymoviesshelf.network.SimpleMovieParcel

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that handles onClick operations
 */
class PosterGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<SimpleMovieParcel, PosterGridAdapter.MoviePropertyViewHolder>(DiffCallback) {
    /**
     * The MoviePropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [SimpleMovieParcel] information.
     */
    class MoviePropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieProperty: SimpleMovieParcel) {
            binding.property = movieProperty
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [SimpleMovieParcel]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<SimpleMovieParcel>() {
        override fun areItemsTheSame(
            oldItem: SimpleMovieParcel,
            newItem: SimpleMovieParcel
        ): Boolean {
            return oldItem === newItem // Special operator that checks if all fields from both objects are a match
        }

        override fun areContentsTheSame(
            oldItem: SimpleMovieParcel,
            newItem: SimpleMovieParcel
        ): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviePropertyViewHolder {
        return MoviePropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MoviePropertyViewHolder, position: Int) {
        val movieProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieProperty)
        }
        holder.bind(movieProperty)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [SimpleMovieParcel]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [SimpleMovieParcel]
     */
    class OnClickListener(val clickListener: (movieProperty: SimpleMovieParcel) -> Unit) {
        fun onClick(movieProperty: SimpleMovieParcel) = clickListener(movieProperty)
    }
}
