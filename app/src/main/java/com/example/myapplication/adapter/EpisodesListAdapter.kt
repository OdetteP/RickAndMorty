package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.models.Episodes
import com.example.myapplication.R
import com.example.myapplication.api.models.EpisodeData
import java.text.SimpleDateFormat
import java.util.*

class EpisodeListAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {
    private val episodes = mutableListOf<EpisodeData>()
    private val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
//    private var endOfListReached = false
//    private var endOfListTextView: TextView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row_episodes, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.textViewName.text = episode.name
        val dateObject = dateFormat.parse(episode.air_date)
        holder.textViewAirDate.text =
            String.format("%td/%tm/%tY", dateObject, dateObject, dateObject)
        holder.textViewCode.text = episode.episode

        holder.itemView.setOnClickListener {
            val fragment = CharactersIdFragment()
            val bundle = Bundle()
            bundle.putStringArrayList("CHARACTERS", episode.characters)
            fragment.arguments = bundle
            val fragmentManager = (it.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }

//        if (position == episodes.size - 1 && !endOfListReached) {
//            endOfListReached = true
//            holder.endOfListTextView?.visibility = View.VISIBLE
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(episodes: Episodes) {
        this.episodes.clear()
        this.episodes.addAll(episodes.results)
//        endOfListReached = false
//        endOfListTextView?.visibility = View.GONE
        notifyDataSetChanged()
    }
}

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewName: TextView = itemView.findViewById(R.id.text_view_id)
    val textViewAirDate: TextView = itemView.findViewById(R.id.text_view_air_date)
    val textViewCode: TextView = itemView.findViewById(R.id.text_view_code)
//    val endOfListTextView: TextView = itemView.findViewById(R.id.end_of_list_text_view)
}

