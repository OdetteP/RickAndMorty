package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class CharacterDetailPage: Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(CharacterDetailViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { val view = inflater.inflate(R.layout.fragment_character_details_page, container, false)
        val textViewName: TextView = view.findViewById(R.id.text_view_name)
        val imagePicture: ImageView = view.findViewById(R.id.image)
        val textViewStatus: TextView = view.findViewById(R.id.text_view_status)
        val textViewSpecies: TextView = view.findViewById(R.id.text_view_species)
        val textViewOrigin: TextView = view.findViewById(R.id.text_view_origin)

        viewModel.characters.observe(viewLifecycleOwner, Observer { characterData ->
            textViewName.text = characterData.name
            Picasso.get().load(characterData.image).into(imagePicture)
            textViewStatus.text = characterData.status
            textViewSpecies.text = characterData.species
            textViewOrigin.text = characterData.origin.name
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
        })

        arguments?.let { viewModel.refreshData(characterId = it.getInt("CHARACTER_ID")) }

        return view
    }


}


