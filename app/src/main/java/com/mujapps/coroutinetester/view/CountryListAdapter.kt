package com.mujapps.coroutinetester.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mujapps.coroutinetester.databinding.ItemCountryBinding
import com.mujapps.coroutinetester.model.Country

class CountryListAdapter(var countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountries(newCountries: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountries)
        notifyDataSetChanged()
    }
}