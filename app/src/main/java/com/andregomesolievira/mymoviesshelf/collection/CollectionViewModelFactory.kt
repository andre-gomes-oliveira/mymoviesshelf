package com.andregomesolievira.mymoviesshelf.collection

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andregomesolievira.mymoviesshelf.database.MovieDatabaseDao

/**
 * Simple ViewModel factory that provides the database to the ViewModel.
 */
class CollectionViewModelFactory(
    private val datasource: MovieDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            return CollectionViewModel(datasource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}