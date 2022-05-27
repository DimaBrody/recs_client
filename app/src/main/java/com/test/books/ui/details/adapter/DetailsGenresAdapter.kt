package com.test.books.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.data.model.GenreChip

class DetailsGenresAdapter(
    private val items: MutableList<GenreChip> = mutableListOf()
) : RecyclerView.Adapter<DetailsGenresAdapter.GenresViewHolder>() {


    class GenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(genre: GenreChip) {
            itemView.findViewById<TextView>(R.id.item_details_genre_text).text = genre.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder =
        GenresViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_details_genre, parent, false)
        )

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}