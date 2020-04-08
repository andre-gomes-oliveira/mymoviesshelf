package com.andregomesolievira.mymoviesshelf.network

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.andregomesolievira.mymoviesshelf.overview.OMDbApiStatus
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Gets Movies information from the OMDb API Retrofit service and updates the
 * [OMDbApiMovie] and [OMDbApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 * @param filter the [OMDbApiFilter] that is sent as part of the web server request
 */
@Parcelize
data class OMDbApiMovie(
    //Using the JSON annotation to rename the properties to camelCase standards
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "Rated") val rated: String,
    @Json(name = "Released") val released: String,
    @Json(name = "Runtime") val runtime: String,
    @Json(name = "Genre") val genre: String,
    @Json(name = "Director") val director: String,
    @Json(name = "Writer") val writer: String,
    @Json(name = "Actors") val actors: String,
    @Json(name = "Plot") val plot: String,
    @Json(name = "Language") val language: String,
    @Json(name = "Country") val country: String,
    @Json(name = "Awards") val awards: String,
    @Json(name = "Poster") val poster: String,
    @Json(name = "Ratings") val ratings: List<Rating>,
    @Json(name = "Metascore") val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    @Json(name = "Type") val type: String,
    @Json(name = "DVD") val dvd: String,
    @Json(name = "BoxOffice") val boxOffice: String,
    @Json(name = "Production") val production: String,
    @Json(name = "Website") val website: String,
    @Json(name = "Response") val response: String
) : Parcelable {
    //An inner class that defines the Ratings property of the movie
    @Parcelize
    data class Rating(
        @Json(name = "Source") val source: String,
        @Json(name = "Value") val value: String
    ) : Parcelable
}