package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.ui.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CityFragment())
                .commit()
        }
    }
}

//    private lateinit var recyclerView: RecyclerView


//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main){

//        recyclerView = findViewById(R.id.city_list_view)
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://us-central1-mobile-assignment-server.cloudfunctions.net/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val weatherApi = retrofit.create(WeatherApi::class.java)
//
//        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
//        swipeRefreshLayout.setOnRefreshListener {
//
//            val call = weatherApi.getCities()
//            call.enqueue(object : Callback<List<WeatherData>> {
//                override fun onResponse(
//                    call: Call<List<WeatherData>>,
//                    response: Response<List<WeatherData>>
//                ) {
//
//                    val weatherData = response.body()
//
//                    if (weatherData != null) {
//                        val cityAdapter = CityListAdapter()
//                        cityAdapter.setData(weatherData.sortedBy { it.city.name })
//
//                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//                        recyclerView.adapter = cityAdapter
//                    }
//                    swipeRefreshLayout.isRefreshing = false
//                }
//
//                override fun onFailure(call: Call<List<WeatherData>>, t: Throwable) {
//                    // Handle the error.
//                    swipeRefreshLayout.isRefreshing = false
//                }
//            })
//        }
//    }





