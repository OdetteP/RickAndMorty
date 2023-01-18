package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class EpisodesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: EpisodesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_episodes, container, false)
        recyclerView = view.findViewById(R.id.city_list_view)
        val episodeAdapter = EpisodeListAdapter()

        viewModel = ViewModelProvider(this).get(EpisodesViewModel::class.java)
        viewModel.episodes.observe(viewLifecycleOwner, Observer { episodeData ->
            episodeAdapter.setData(episodeData)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = episodeAdapter
        })

        viewModel.refreshData()

        return view
    }
}




