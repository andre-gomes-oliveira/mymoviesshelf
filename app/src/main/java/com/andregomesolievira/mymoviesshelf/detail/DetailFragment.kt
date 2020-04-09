package com.andregomesolievira.mymoviesshelf.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.andregomesolievira.mymoviesshelf.database.MovieDatabase
import com.andregomesolievira.mymoviesshelf.databinding.DetailFragmentBinding

/**
 * This [Fragment] shows the detailed information about a selected movie.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */
class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val dataSource = MovieDatabase.getInstance(application).movieDatabaseDao
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val movie = DetailFragmentArgs.fromBundle(arguments!!).selectedMovie
        val viewModelFactory = DetailViewModelFactory(movie, dataSource, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)
        return binding.root
    }
}