package com.mike.mvpapptracker.ui.contract

import com.mike.mvpapptracker.response.PopularMoviesListResponse
import com.mike.mvpapptracker.ui.presenter.BasePresenter
import com.mike.mvpapptracker.ui.view.BaseView

interface  MainFragmentContract {

    interface MainFragmentView : BaseView {
        fun showPopularMovies(data : PopularMoviesListResponse)
    }

    interface MainFragmentPresenter : BasePresenter {
        fun getPopularMovies(page: Int)
    }
}