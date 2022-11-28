package com.mike.mvpapptracker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mike.core.common.AppTrackerDialogBuilder
import com.mike.core.util.gone
import com.mike.core.util.visible
import com.mike.mvpapptracker.adapter.PopularMoviesAdapter
import com.mike.mvpapptracker.databinding.FragmentMainBinding
import com.mike.mvpapptracker.response.PopularMoviesListResponse
import com.mike.mvpapptracker.ui.contract.MainFragmentContract
import com.mike.mvpapptracker.ui.presenter.MainFragmentPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), MainFragmentContract.MainFragmentView {

    lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var popularMoviesAdapter: PopularMoviesAdapter

    @Inject
    lateinit var mainPresenter: MainFragmentPresenter

    private lateinit var errorDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        mainPresenter.getPopularMovies(1)
    }

    private fun initUi() {
        errorDialog =
            AppTrackerDialogBuilder(requireContext()).setDialogType(AppTrackerDialogBuilder.DialogType.WARNING)
                .setTitle(getString(com.mike.core.R.string.error))
                .setMessage(getString(com.mike.core.R.string.sync_again))
                .setNegativeButton(text = getString(com.mike.core.R.string.cancel)) {
                    it?.hide()
                }
                .setPositiveButton(text = getString(com.mike.core.R.string.acept)) {
                    it?.hide()
                }
                .create()
    }


    override fun showPopularMovies(data: PopularMoviesListResponse) {
        binding.apply {
            lifecycleScope.launchWhenCreated {
                popularMoviesAdapter.bind(data.results)
            }
            rvMovies.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = popularMoviesAdapter
            }
        }
    }

    override fun loadingData() {
        binding.apply {
            moviesLoading.visible()
        }
    }

    override fun dataLoaded() {
        binding.apply {
            moviesLoading.gone()
        }
    }

    override fun showErrorMessage(error: String) {
        errorDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDestroy()
        errorDialog.dismiss()
    }

    override fun onDetach() {
        super.onDetach()
        mainPresenter.onDetached()
        errorDialog.dismiss()
    }

}