package com.mike.mvpapptracker.ui.presenter

import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

open class BasePresenterImpl : BasePresenter {

    @NonNull
    var disposable: Disposable? = null

    override fun onDetached() {
        disposable?.let {
            it.dispose()
        }
    }

    override fun onDestroy() {
        disposable?.let {
            it.dispose()
        }
    }

}