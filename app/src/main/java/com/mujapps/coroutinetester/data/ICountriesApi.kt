package com.mujapps.coroutinetester.data

import com.mujapps.coroutinetester.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ICountriesApi {
    //https://raw.githubusercontent.com/DevTides/countries/master/countriesV2.json
    @GET("countries/master/countriesV2.json")
    suspend fun getAllCountries() : Response<List<Country>>
}