package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class WeatherDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityName = arguments?.getString("CITY_NAME")
        val pictureUrl = arguments?.getString("CITY_PICTURE")
        val tempType = arguments?.getString("CITY_TEMP_TYPE")
        val temp = arguments?.getDouble("CITY_TEMP")

        val textViewName: TextView = view.findViewById(R.id.text_view_city_name)
        val imagePicture: ImageView = view.findViewById(R.id.image)
        val textViewTemputure: TextView = view.findViewById(R.id.text_view_temperature)

        textViewName.text = cityName
        Picasso.get().load(pictureUrl).into(imagePicture)
        val celsiusTemp: Double? = when (tempType) {
            "K" -> temp?.minus(273.15)
            "F" -> (temp?.minus(32/ 1.8))
            else -> temp
        }
        textViewTemputure.text = String.format("%.2f", celsiusTemp)
    }
}