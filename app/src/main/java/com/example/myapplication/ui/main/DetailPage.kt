package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.api.models.CharacterData
import com.squareup.picasso.Picasso

class DetailPage: Fragment() {

    private lateinit var viewModel: CharactersViewModel

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

            val textViewName: TextView = view.findViewById(R.id.text_view_city_name)
            val imagePicture: ImageView = view.findViewById(R.id.image)
            val textViewStatus: TextView = view.findViewById(R.id.text_view_temperature)

            viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
            viewModel.characters.observe(viewLifecycleOwner, Observer { characterData ->
                 textViewName.text = characterData.name
            Picasso.get().load(characterData.image).into(imagePicture)
            textViewStatus.text = characterData.status
            })

            arguments?.let { viewModel.refreshData(characterId = it.getInt("CHARACTER_ID")) }


        }
    }


