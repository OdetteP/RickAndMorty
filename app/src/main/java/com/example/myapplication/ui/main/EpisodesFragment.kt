package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class EpisodesFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(EpisodesViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { val view = inflater.inflate(R.layout.recycler_view, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_list)
        val episodeAdapter = EpisodeListAdapter()

        viewModel.episodes.observe(viewLifecycleOwner, Observer { episodeData ->
            episodeAdapter.setData(episodeData)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = episodeAdapter
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == recyclerView.adapter?.itemCount?.minus(1)) {
                    Toast.makeText(context, "Reached end of list", Toast.LENGTH_SHORT).show()
                    // This text could come out of the strings.xml file when working with a big project
                }
            }
        })

        viewModel.refreshData()

        return view
    }
}




