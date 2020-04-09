package com.andregomesolievira.mymoviesshelf.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines a movie table on the Room Database
 *
 * Annonations are used to map properties in the class to the columns in the database
 */
@Entity(tableName = "movie_table")
data class MovieEntity(

    // The primary key of this table is the imdbID field,
    @PrimaryKey val imdbID: String,

    /**
     * The tile and the poster of the movie are the informations that will be stored
     * Any additional information will be queried when necessary from the [OMDBApiService]
     * */
    @ColumnInfo(name = "movie_title") val title: String,
    @ColumnInfo(name = "movie_poster") val poster: String
)