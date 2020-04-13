package com.andregomesolievira.mymoviesshelf.detail

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.andregomesolievira.mymoviesshelf.R
import com.andregomesolievira.mymoviesshelf.database.MovieDatabaseDao
import com.andregomesolievira.mymoviesshelf.database.MovieEntity
import com.andregomesolievira.mymoviesshelf.network.OMDbApiMovie
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [OMDbApiMovie] and the [MovieDatabaseDao].
 */
class DetailViewModel(
    omdbMovieProperty: OMDbApiMovie,
    private val database: MovieDatabaseDao,
    app: Application
) :
    AndroidViewModel(app) {
    private val _selectedMovie = MutableLiveData<OMDbApiMovie>()

    // The external LiveData for the SelectedProperty
    val selectedMovie: LiveData<OMDbApiMovie>
        get() = _selectedMovie

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedMovie.value = omdbMovieProperty
    }

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var isMovieLiked = false;

    /** Adapting all the properties to be displayed on the screen, with the proper translation when possible */
    val displayPropertyTitle = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_title) + it.title
    }

    val displayPropertyYear = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_year) + it.year
    }


    val displayPropertyRated = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_rated) + it.rated
    }

    val displayPropertyReleased = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_released) + it.released
    }

    val displayPropertyRuntime = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_runtime) + it.runtime
    }

    val displayPropertyGenre = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_genre) + it.genre
    }

    val displayPropertyDirector = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_director) + it.director
    }

    val displayPropertyWriter = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_writer) + it.writer
    }

    val displayPropertyActors = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_actors) + it.actors
    }

    val displayPropertyPlot = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_plot) + it.plot
    }

    val displayPropertyLanguage = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_language) + it.language
    }

    val displayPropertyCountry = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_country) + it.country
    }

    val displayPropertyAwards = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_awards) + it.awards
    }

    val displayPropertyRatings = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_ratings) + it.ratings
    }

    val displayPropertyDiscRelease = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_dvd) + it.dvd
    }

    val displayPropertyBoxOffice = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_box_office) + it.boxOffice
    }

    val displayPropertyProduction = Transformations.map(selectedMovie) {
        app.applicationContext.getString(R.string.movie_box_production) + it.production
    }

    /**The functions that will either insert or delete the new movie in the [MovieDatabase] */

    private suspend fun insert(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            database.insert(movie)
        }
    }

    private suspend fun delete(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            database.insert(movie)
        }
    }

    /**
     * Executes when the FAB button is clicked
     */
    fun onFABClicked(view: View) {
        val fab = view.findViewById<FloatingActionButton>(R.id.save_fab)
        isMovieLiked = if (isMovieLiked) {
            onMovieDisliked()
            fab.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            false
        } else {
            onMovieLiked()
            fab.setImageResource(R.drawable.ic_favorite_black_24dp)
            true
        }
    }

    /**
     * Executes when the FAB button is clicked to like a movie.
     */
    private fun onMovieLiked() {
        coroutineScope.launch {
            // Create a new movie entity, with information from the selected movie,
            // and insert it into the database.
            val newMovie = selectedMovie.value?.let {
                MovieEntity(
                    it.imdbID,
                    it.title,
                    it.poster
                )
            }

            newMovie?.let { insert(newMovie) }
        }
    }

    /**
     * Executes when the FAB button is clicked to like a movie.
     */
    private fun onMovieDisliked() {
        coroutineScope.launch {
            // Create a new movie entity, with information from the selected movie,
            // and insert it into the database.
            val newMovie = selectedMovie.value?.let {
                MovieEntity(
                    it.imdbID,
                    it.title,
                    it.poster
                )
            }

            newMovie?.let { delete(newMovie) }
        }
    }
}