package com.andregomesolievira.mymoviesshelf.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Parent object of a [SimpleMovieParcel] that comes as the result of the search query parameter
 */
@Parcelize
class SearchParcel(
    @Json(name = "Search") val movies: List<SimpleMovieParcel>
) : Parcelable