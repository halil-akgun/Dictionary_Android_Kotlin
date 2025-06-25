package com.example.dictionary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(private val context: Context, private val wordList: List<Word>) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var wordCard: CardView = view.findViewById(R.id.word_card)
        var textViewEnglish: TextView = view.findViewById(R.id.textViewEnglish)
        var textViewTurkish: TextView = view.findViewById(R.id.textViewTurkish)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val word = wordList[position]
        holder.textViewEnglish.text = word.english
        holder.textViewTurkish.text = word.turkish

        holder.wordCard.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}