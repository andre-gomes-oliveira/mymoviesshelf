package com.andregomesolievira.mymoviesshelf.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andregomesolievira.mymoviesshelf.database.MovieDatabaseDao
import com.andregomesolievira.mymoviesshelf.network.OMDbApiMovie

/**
 * Simple ViewModel factory that provides the movie and context to the ViewModel.
 */
class DetailViewModelFactory(
    private val movie: OMDbApiMovie,
    private val datasource: MovieDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie, datasource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
