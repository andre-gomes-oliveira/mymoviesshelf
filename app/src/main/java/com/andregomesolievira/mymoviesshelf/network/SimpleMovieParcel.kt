package com.andregomesolievira.mymoviesshelf.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimpleMovieParcel(
    @Json(name = "Title") val title: String,
    val imdbID: String,
    @Json(name = "Poster") val poster: String
) : Parcelable
