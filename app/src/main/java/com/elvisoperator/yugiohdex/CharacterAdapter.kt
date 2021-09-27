package com.elvisoperator.yugiohdex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.databinding.ItemCardsBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(val character: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    class CharacterHolder(val binding: ItemCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(character: Character) {
            binding.tvName.text = character.name
            binding.tvType.text = character.type
            Picasso.get().load(character.image).into(binding.ivCharacter)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterHolder {
        val binding = ItemCardsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterHolder, position: Int) {
        holder.render(character[position])
    }

    override fun getItemCount(): Int = character.size


}