package com.mike.mvpapptracker.di

import androidx.fragment.app.Fragment
import com.mike.mvpapptracker.ui.contract.MainFragmentContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ContractsModule {

    @Provides
    fun mainView(fragment: Fragment): MainFragmentContract.MainFragmentView {
        return fragment as MainFragmentContract.MainFragmentView
    }


}