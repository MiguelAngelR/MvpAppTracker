package com.mike.mvpapptracker.ui.view

interface BaseView {
    fun loadingData()
    fun dataLoaded()
    fun showErrorMessage(error: String)
}