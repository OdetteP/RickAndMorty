package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CharactersIdFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recycler_view, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_list)

        val characters = arguments?.getStringArrayList("CHARACTERS")
        val episodeAdapter = CharactersListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = episodeAdapter
        characters?.let { episodeAdapter.setData(it.toList()) }

        return view
    }
}