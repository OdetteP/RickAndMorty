package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.models.Episodes
import com.example.myapplication.R
import com.example.myapplication.api.models.ResultData

class CityListAdapter : RecyclerView.Adapter<CityViewHolder>() {
    private val cities = mutableListOf<ResultData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.textViewName.text = city.name
        holder.textViewAirDate.text = city.air_date
        holder.textViewCode.text = city.episode

//        holder.itemView.setOnClickListener {
//            val fragment = WeatherDetailsFragment()
//            val bundle = Bundle()
//            bundle.putString("CITY_NAME", city.city.name)
//            bundle.putString("CITY_PICTURE", city.city.picture)
//            bundle.putString("CITY_TEMP_TYPE", city.tempType)
//            bundle.putDouble("CITY_TEMP", city.temp)
//            fragment.arguments = bundle
//            val fragmentManager = (it.context as AppCompatActivity).supportFragmentManager
//            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cities: Episodes) {
        this.cities.clear()
        this.cities.addAll(cities.results)
        notifyDataSetChanged()
    }
}

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewName: TextView = itemView.findViewById(R.id.text_view_name)
    val textViewAirDate: TextView = itemView.findViewById(R.id.text_view_air_date)
    val textViewCode: TextView = itemView.findViewById(R.id.text_view_code)
}

