package com.elvisoperator.yugiohdex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.databinding.ItemCardsBinding

class CharacterAdapter(private val character: MutableList<Data>) :
    RecyclerView.Adapter<CharacterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterHolder(layoutInflater.inflate(R.layout.item_cards, parent , false))

    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
     val item = character[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return character.size
    }


}