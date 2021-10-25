package com.elvisoperator.yugiohdex.ui.fragments

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.model.BasicCard

import com.elvisoperator.yugiohdex.databinding.ItemCardsBinding

import com.elvisoperator.yugiohdex.data.model.Data
//import ar.edu.unlam.yugiohdex.databinding.ItemCardsBinding
import com.squareup.picasso.Picasso


class CardHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var recyclerviewMovieBinding: ItemCardsBinding


    val binding = ItemCardsBinding.bind(view)
    fun bind(card: BasicCard) {

        binding.tvName.text = card.name
        binding.tvType.text = card.type
        binding.level.text = card.level.toString()
        Picasso.get().load(card.image.image_url).into(binding.ivCards)
        Log.println(Log.INFO, "Valor Imagen", card.image.image_url_small)
        val id = if(card.fav) R.drawable.ic_favorite
        else R.drawable.ic_favorite_filled
    }
}