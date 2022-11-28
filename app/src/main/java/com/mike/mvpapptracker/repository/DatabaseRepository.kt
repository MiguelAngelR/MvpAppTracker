package com.mike.mvpapptracker.repository

import com.mike.mvpapptracker.db.MoviesDao
import com.mike.mvpapptracker.db.MoviesEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val dao: MoviesDao) {
    fun insertMovie(entity: MoviesEntity) = dao.insertMovie(entity)
    fun deleteMovie(entity: MoviesEntity) = dao.deleteMovie(entity)
}