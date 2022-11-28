package com.mike.mvpapptracker.api

import com.mike.mvpapptracker.response.PopularMoviesListResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<Response<PopularMoviesListResponse>>

}