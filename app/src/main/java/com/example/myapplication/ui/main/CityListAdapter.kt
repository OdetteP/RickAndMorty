package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.util.*

class CityListAdapter : RecyclerView.Adapter<CityViewHolder>() {
    private val cities = mutableListOf<WeatherData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

//    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
//        val city = cities[position]
//        holder.textViewCityName.text = city.city.name
//    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.textViewCityName.text = city.city.name

        holder.itemView.setOnClickListener {
            val fragment = WeatherDetailsFragment()
            val bundle = Bundle()
            bundle.putString("CITY_NAME", city.city.name)
            bundle.putString("CITY_PICTURE", city.city.picture)
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
}



//    (private val cities: ArrayList<City>) :
//    RecyclerView.Adapter<CityListAdapter.ViewHolder>() {
//
//    private var citiesFilterArray: ArrayList<City>
//
//    init {
//        citiesFilterArray = cities
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(
//            R.layout.recyclerview_item_row, parent, false
//        )
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = citiesFilterArray[position]
////        holder.cityName.text = item.name
////        holder.country.text = item.country
////        holder.latitude.text = item.latitude.toString()
////        holder.longitude.text = item.longitude.toString()
//
//        holder.itemView.setOnClickListener {
//            //TODO: Add detailPage
//        }
//    }
//
//    override fun getItemCount(): Int = citiesFilterArray.size
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
////        val cityName: TextView = view.findViewById(R.id.itemTitleCity)
////        val country: TextView = view.findViewById(R.id.itemTitleCountryCode)
////        val latitude: TextView = view.findViewById(R.id.subtitleLatitude)
////        val longitude: TextView = view.findViewById(R.id.subtitleLongitude)
//    }
//
////    override fun getFilter(): Filter {
////        return object : Filter() {
////            override fun performFiltering(constraint: CharSequence?): FilterResults {
////                val charSearch = constraint.toString()
////                citiesFilterArray = if (charSearch.isEmpty()) {
////                    cities
////                } else {
////                    cities.filter {
////                        it.name?.lowercase(Locale.ROOT)?.startsWith(
////                            charSearch.lowercase(Locale.ROOT)) == true
////                    } as ArrayList<City>
////                }
////                val filterResults = FilterResults()
////                filterResults.values = citiesFilterArray
////                return filterResults
////            }
////
////            @SuppressLint("NotifyDataSetChanged")
////            @Suppress("UNCHECKED_CAST")
////            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
////                citiesFilterArray = results?.values as ArrayList<City>
////                notifyDataSetChanged()
////            }
////        }
////    }
//
//
//}

//
//    : RecyclerView.Adapter<CityViewHolder>() {
//    private val cities = mutableListOf<City>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item_view, parent, false)
//        return CityViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return cities.size
//    }
//
//    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
//        val city = cities[position]
//        holder.textViewCityName.text = city.name
//    }
//
//    fun setData(cities: List<City>) {
//        this.cities.clear()
//        this.cities.addAll(cities)
//        notifyDataSetChanged()
//    }



//    (private val cities: List<String>) :
//    RecyclerView.Adapter<CityListAdapter.ViewHolder>() {
//
//    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val textView = LayoutInflater.from(parent.context)
//            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
//        return ViewHolder(textView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textView.text = cities[position]
//    }
//
//    override fun getItemCount() = cities.size

