package com.andregomesolievira.mymoviesshelf.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andregomesolievira.mymoviesshelf.network.OMDbApi
import com.andregomesolievira.mymoviesshelf.network.OMDbApiMovie
import com.andregomesolievira.mymoviesshelf.network.SimpleMovieParcel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class OMDbApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [MovieFragment].
 */

class OverviewViewModel : ViewModel() {

    //Using a simple string property for two-way data binding
    private var search: String? = null

    fun getSearch(): String? {
        return search
    }

    fun setSearch(newSearch: String) {
        search = newSearch
        getMovieProperties(newSearch)
    }

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<OMDbApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<OMDbApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of movies
    private val _properties = MutableLiveData<List<SimpleMovieParcel>>()

    // The external LiveData interface to the property is immutable, so only this class can modify it
    val properties: LiveData<List<SimpleMovieParcel>>
        get() = _properties

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<OMDbApiMovie>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<OMDbApiMovie>
        get() = _navigateToSelectedProperty

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getMovieProperties() on init so we can display status immediately.
     */
    init {
        getMovieProperties("Batman") // Initializing with the best search :)
    }

    /**
     * Gets filtered movie property information from the OMDb API Retrofit service and
     * updates the [SimpleMovieParcel] [List] and [OMDbApiStatus] [LiveData].
     * @param title the movie title that is sent as part of the web server request
     */
    private fun getMovieProperties(search: String) {
        coroutineScope.launch {
            val getPropertiesDeferred = OMDbApi.retrofitService.getMoviesAsync(search, "f19bb759")
            try {
                _status.value = OMDbApiStatus.LOADING
                val searchResult = getPropertiesDeferred.await()
                _status.value = OMDbApiStatus.DONE
                _properties.value = searchResult.movies
            } catch (e: Exception) {
                _status.value = OMDbApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    /**
     * When the [ViewModel] is finished, the coroutine [viewModelJob] must be cancelled
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedProperty] [MutableLiveData]
     * @param SimpleMovieParcel The [SimpleMovieParcel] that was clicked on.
     */
    fun displayPropertyDetails(movieProperty: SimpleMovieParcel) {
        coroutineScope.launch {
            val getMovieDetailsDeferred =
                OMDbApi.retrofitService.getMovieDetailsAsync(movieProperty.title, "f19bb759")
            _navigateToSelectedProperty.value = getMovieDetailsDeferred.await()
        }
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}