package com.andregomesolievira.mymoviesshelf.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andregomesolievira.mymoviesshelf.network.OMDbApiMovie

/**
 * Simple ViewModel factory that provides the movie and context to the ViewModel.
 */
class DetailViewModelFactory(
    private val movie: OMDbApiMovie,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
