package com.mujapps.coroutinetester.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujapps.coroutinetester.databinding.ActivityMainBinding
import com.mujapps.coroutinetester.view_model.MainCountryListViewModel

class MainActivity : AppCompatActivity() {

    private val mMainViewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mMainViewModel: MainCountryListViewModel by viewModels()

    private val mCountriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mMainViewBinding.root)

        mMainViewBinding.mLstCountries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mCountriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {

        mMainViewModel.refresh()
        mMainViewModel.countryFetchLoading.observe(this) {
            if (it) mMainViewBinding.mProgressMain.visibility = View.VISIBLE
            else mMainViewBinding.mProgressMain.visibility = View.GONE
        }

        mMainViewModel.errorMessage.observe(this) {
            mMainViewBinding.mLblError.text = it
            mMainViewBinding.mLstCountries.visibility = View.GONE
            mMainViewBinding.mLblError.visibility = View.VISIBLE
        }

        mMainViewModel.countries.observe(this) {
            mCountriesAdapter.updateCountries(it)
            mMainViewBinding.mLstCountries.visibility = View.VISIBLE
            mMainViewBinding.mLblError.visibility = View.GONE
        }
    }
}