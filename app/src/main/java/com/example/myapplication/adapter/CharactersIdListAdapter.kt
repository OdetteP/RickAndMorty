package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CharactersListAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    private val characters = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position].removePrefix("https://rickandmortyapi.com/api/character/")
        holder.textViewName.text = character

        holder.itemView.setOnClickListener {
            val fragment = CharacterDetailPage()
            val bundle = Bundle()
            bundle.putInt("CHARACTER_ID", character.toInt())
            fragment.arguments = bundle
            val fragmentManager = (it.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(characters: List<String>) {
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }
}

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewName: TextView = itemView.findViewById(R.id.text_view_id)
}

