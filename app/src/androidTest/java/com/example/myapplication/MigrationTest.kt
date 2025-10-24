package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.feature.movie_pages.data.database.MoviesRoomDatabase
import com.example.myapplication.feature.movie_pages.data.database.entity.MovieEntity
import com.example.myapplication.feature.movie_pages.data.database.entity.WatchLaterEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private lateinit var db: MoviesRoomDatabase
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setup() {
        // Borrar base de datos anterior
        context.deleteDatabase("test_db")

        // Crear nueva base de datos con la versión actual (2)
        db = Room.databaseBuilder(
            context,
            MoviesRoomDatabase::class.java,
            "test_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @After
    fun tearDown() {
        db.close()
        context.deleteDatabase("test_db")
    }

    @Test
    fun testDatabaseCreation_HasBothTables() = runBlocking {
        // Verificar que podemos insertar en movies
        val movie = MovieEntity(
            title = "Test Movie",
            posterPath = "/test.jpg",
            timestamp = System.currentTimeMillis()
        )
        db.movieDao().insert(movie)

        val movies = db.movieDao().getMovies()
        assertEquals("Debe haber 1 película", 1, movies.size)
        assertEquals("Test Movie", movies[0].title)
    }

    @Test
    fun testWatchLaterTable_CanInsertAndRetrieve() = runBlocking {
        // Insertar en watch_later
        val watchLater = WatchLaterEntity(
            movieTitle = "Watch Later Movie",
            posterPath = "/watch.jpg",
            addedTimestamp = System.currentTimeMillis()
        )
        db.watchLaterDao().addToWatchLater(watchLater)

        val watchLaterMovies = db.watchLaterDao().getWatchLaterMovies().first()
        assertEquals("Debe haber 1 película en watch later", 1, watchLaterMovies.size)
        assertEquals("Watch Later Movie", watchLaterMovies[0].movieTitle)
    }

    @Test
    fun testWatchLater_CanDeleteMovie() = runBlocking {
        // Insertar
        val watchLater = WatchLaterEntity(
            movieTitle = "To Delete",
            posterPath = "/delete.jpg",
            addedTimestamp = System.currentTimeMillis()
        )
        db.watchLaterDao().addToWatchLater(watchLater)

        // Verificar que existe
        var isInList = db.watchLaterDao().isInWatchLater("To Delete")
        assertTrue("Debe estar en la lista", isInList)

        // Eliminar
        db.watchLaterDao().removeFromWatchLater("To Delete")

        // Verificar que ya no existe
        isInList = db.watchLaterDao().isInWatchLater("To Delete")
        assertFalse("No debe estar en la lista", isInList)
    }

    @Test
    fun testBothTables_WorkIndependently() = runBlocking {
        // Insertar en ambas tablas
        val movie = MovieEntity(
            title = "Regular Movie",
            posterPath = "/regular.jpg",
            timestamp = System.currentTimeMillis()
        )
        db.movieDao().insert(movie)

        val watchLater = WatchLaterEntity(
            movieTitle = "Watch Later Movie",
            posterPath = "/watch.jpg",
            addedTimestamp = System.currentTimeMillis()
        )
        db.watchLaterDao().addToWatchLater(watchLater)

        // Verificar ambas
        val movies = db.movieDao().getMovies()
        val watchLaterMovies = db.watchLaterDao().getWatchLaterMovies().first()

        assertEquals("Debe haber 1 película regular", 1, movies.size)
        assertEquals("Debe haber 1 película en watch later", 1, watchLaterMovies.size)
    }
}