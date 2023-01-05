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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.model.WeatherData
import com.example.myapplication.R

class CityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CityViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        recyclerView = view.findViewById(R.id.city_list_view)

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        viewModel.cities.observe(viewLifecycleOwner, Observer { weatherData ->
            val cityAdapter = CityListAdapter()
            cityAdapter.setData(weatherData.sortedWith(
                compareBy<WeatherData> { it.city.name }.thenBy { it.date }))
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = cityAdapter
        })
    }
}




