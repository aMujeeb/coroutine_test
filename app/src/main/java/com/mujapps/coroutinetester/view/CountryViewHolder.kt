package com.mujapps.coroutinetester.view

import androidx.recyclerview.widget.RecyclerView
import com.mujapps.coroutinetester.databinding.ItemCountryBinding
import com.mujapps.coroutinetester.model.Country

class CountryViewHolder(private val view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {
    fun onBind(country: Country) {
        view.mLblCountryName.text = country.name
    }
}