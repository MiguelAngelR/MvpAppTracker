package com.mike.mvpapptracker.ui.presenter

import android.util.Log
import com.mike.mvpapptracker.repository.ApiRepository
import com.mike.mvpapptracker.ui.contract.MainFragmentContract
import com.mike.mvpapptracker.utils.applyIoScheduler
import javax.inject.Inject

class MainFragmentPresenter
@Inject constructor(
    private val repository: ApiRepository,
    val mainFragmentView: MainFragmentContract.MainFragmentView,
) : MainFragmentContract.MainFragmentPresenter, BasePresenterImpl() {


    override fun getPopularMovies(page: Int) {
        disposable = repository
            .getPopularMovies(page)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("MainPresenter", "itBody : $itBody")
                            mainFragmentView.showPopularMovies(itBody)
                        }
                    in 500..599 -> mainFragmentView.showErrorMessage(response.message())
                }
            }

    }

    override fun onDetached() {
        //TODO: Add function to detached fragment an delete presenter
    }

    override fun onDestroy() {
        //TODO: Add function to detached fragment an delete presenter
    }


}