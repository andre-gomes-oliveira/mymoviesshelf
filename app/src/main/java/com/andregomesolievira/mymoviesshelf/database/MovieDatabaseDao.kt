package com.andregomesolievira.mymoviesshelf.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Defines methods for using the [MovieEntity] class with Room.
 *
 * The [Insert] [Update] and [Delete] annotiations will generate the proper queries
 */
@Dao
interface MovieDatabaseDao {
    /**
     * @param movie new value to insert
     */
    @Insert
    fun insert(movie: MovieEntity)

    /**
     * @param movie new value to write
     */
    @Update
    fun update(movie: MovieEntity)

    /**
     * @param movie The movie to delete
     */
    @Delete
    fun delete(movie: MovieEntity)

    /**
     * @param key the imdbId of the movie to be found
     */
    @Query("SELECT * from movie_table WHERE imdbID = :key")
    fun get(key: String): MovieEntity?

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by title in descending order.
     */
    @Query("SELECT * FROM movie_table ORDER BY movie_title DESC")
    fun getAllMovies(): LiveData<List<MovieEntity>>
}