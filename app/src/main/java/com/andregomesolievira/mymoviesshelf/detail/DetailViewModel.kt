package com.andregomesolievira.mymoviesshelf.detail

import android.app.Application
import androidx.lifecycle.*
import com.andregomesolievira.mymoviesshelf.R
import com.andregomesolievira.mymoviesshelf.network.SimpleMovieParcel

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [SimpleMovieParcel].
 */
class DetailViewModel(omdbMovieProperty: SimpleMovieParcel, app: Application) :
    AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<SimpleMovieParcel>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<SimpleMovieParcel>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = omdbMovieProperty
    }

    //Adapting all the properties to be displayed on the screen, with the proper translation when possible
    val displayPropertyTitle = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_title) + it.title
    }

    val displayPropertyYear = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_year) + it.year
    }

    /*
    val displayPropertyRated = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_rated) + it.rated
    }

    val displayPropertyReleased = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_released) + it.released
    }

    val displayPropertyRuntime = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_runtime) + it.runtime
    }

    val displayPropertyGenre = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_genre) + it.genre
    }

    val displayPropertyDirector = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_director) + it.director
    }

    val displayPropertyWriter = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_writer) + it.writer
    }

    val displayPropertyActors = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_actors) + it.actors
    }

    val displayPropertyPlot = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_plot) + it.plot
    }

    val displayPropertyLanguage = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_language) + it.language
    }

    val displayPropertyCountry = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_country) + it.country
    }

    val displayPropertyAwards = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_awards) + it.awards
    }

    val displayPropertyRatings = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_ratings) + it.ratings
    }

    val displayPropertyDiscRelease = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_dvd) + it.dvd
    }

    val displayPropertyBoxOffice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_box_office) + it.boxOffice
    }

    val displayPropertyProduction = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.movie_box_production) + it.production
    }
     */
}