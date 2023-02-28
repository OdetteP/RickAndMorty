package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.recycler_view.*
import kotlinx.android.synthetic.main.recycler_view.view.*

class CharactersIdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recycler_view, container, false)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val characters = arguments?.getStringArrayList("CHARACTERS")
        val episodeAdapter = CharactersListAdapter()
        view.recycler_view_list.layoutManager = LinearLayoutManager(activity)
        view.recycler_view_list.adapter = episodeAdapter
        characters?.let { episodeAdapter.setData(it.toList()) }

        return view
    }
}