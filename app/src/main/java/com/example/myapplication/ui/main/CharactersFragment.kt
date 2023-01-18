package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CharactersFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)

        var characters = arguments?.getStringArrayList("CHARACTERS")
        recyclerView = view.findViewById(R.id.view_characters)
        val episodeAdapter = CharactersListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = episodeAdapter
        episodeAdapter.setData(characters!!.toList())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val charactersId = arguments?.getStringArrayList("CHARACTERS")
    }
}