package com.andregomesolievira.mymoviesshelf

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.andregomesolievira.mymoviesshelf.database.MovieDatabase
import com.andregomesolievira.mymoviesshelf.database.MovieDatabaseDao
import com.andregomesolievira.mymoviesshelf.database.MovieEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private lateinit var sleepDao: MovieDatabaseDao
    private lateinit var db: MovieDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        sleepDao = db.movieDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val movie = MovieEntity(
            "tt0325980",
            "Pirates of the Caribbean: The Curse of the Black Pearl",
            "https://m.media-amazon.com/images/M/MV5BNGYyZGM5MGMtYTY2Ni00M2Y1LWIzNjQtYWUzM2VlNGVhMDNhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
        )
        sleepDao.insert(movie)
        val testMovie = sleepDao.get("tt0325980")
        assertEquals(testMovie?.title, "Pirates of the Caribbean: The Curse of the Black Pearl")
    }
}

