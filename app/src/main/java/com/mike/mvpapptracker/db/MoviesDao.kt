package com.mike.mvpapptracker.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(entity: MoviesEntity): Completable

    @Delete
    fun deleteMovie(entity: MoviesEntity): Completable

    @Query("SELECT * From popular_movies")
    fun getAllMovies(): Observable<MutableList<MoviesEntity>>


}