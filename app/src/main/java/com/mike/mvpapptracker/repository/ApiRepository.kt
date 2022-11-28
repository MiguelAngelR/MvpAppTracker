package com.mike.mvpapptracker.repository

import com.mike.mvpapptracker.api.ApiServices
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    fun getPopularMovies(page: Int) = apiServices.getPopularMovies(page)
}