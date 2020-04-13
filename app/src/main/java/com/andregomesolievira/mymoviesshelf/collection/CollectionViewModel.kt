package com.andregomesolievira.mymoviesshelf.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andregomesolievira.mymoviesshelf.database.MovieDatabaseDao
import com.andregomesolievira.mymoviesshelf.database.MovieEntity
import com.andregomesolievira.mymoviesshelf.overview.OMDbApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext


enum class OMDbApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [CollectionFragment].
 */
class CollectionViewModel(
    private val database: MovieDatabaseDao,
    app: Application
) : AndroidViewModel(app) {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<OMDbApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<OMDbApiStatus>
        get() = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val properties = database.getAllMovies()

    init {
    }

    private suspend fun getMoviesFromDatabase(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            val movies = database.getAllMovies()
            movies
        }
    }

}
