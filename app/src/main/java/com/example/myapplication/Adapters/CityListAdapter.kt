package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.WeatherData
import com.example.myapplication.R

class CityListAdapter : RecyclerView.Adapter<CityViewHolder>() {
    private val cities = mutableListOf<WeatherData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.textViewCityName.text = city.city.name
        holder.textViewDate.text = city.date

        holder.itemView.setOnClickListener {
            val fragment = WeatherDetailsFragment()
            val bundle = Bundle()
            bundle.putString("CITY_NAME", city.city.name)
            bundle.putString("CITY_PICTURE", city.city.picture)
            bundle.putString("CITY_TEMP_TYPE", city.tempType)
            bundle.putDouble("CITY_TEMP", city.temp)
            fragment.arguments = bundle
            val fragmentManager = (it.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cities: List<WeatherData>) {
        this.cities.clear()
        this.cities.addAll(cities)
        notifyDataSetChanged()
    }
}

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewCityName: TextView = itemView.findViewById(R.id.text_view_city_name)
    val textViewDate: TextView = itemView.findViewById(R.id.text_view_date)
}

