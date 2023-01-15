package com.mujapps.coroutinetester.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mujapps.coroutinetester.data.CountriesService
import com.mujapps.coroutinetester.model.Country
import kotlinx.coroutines.*

class MainCountryListViewModel : ViewModel() {

    var countries = MutableLiveData<ArrayList<Country>>()
    var countryFetchLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    private val mCountriesService = CountriesService().getCountriesService()
    private var mJob: Job? = null

    //Coroutine Exception Handler
    val mExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        countryFetchLoading.value = true

        mJob = CoroutineScope(Dispatchers.IO + mExceptionHandler).launch {
            val response = mCountriesService.getAllCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    errorMessage.value = ""
                    countries.value = response.body()?.let { ArrayList(it) }
                    countryFetchLoading.value = false
                } else {
                    onError(response.message())
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        countryFetchLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        mJob?.cancel()
    }
}