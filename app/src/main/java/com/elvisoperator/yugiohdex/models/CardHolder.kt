package com.elvisoperator.yugiohdex.models

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.Data
import com.elvisoperator.yugiohdex.databinding.ItemCardsBinding
import com.squareup.picasso.Picasso


class CardHolder(view : View):RecyclerView.ViewHolder(view) {

    private  val binding  = ItemCardsBinding.bind(view)
    fun bind(card : Data){

        binding.tvName.text = card.name
        binding.tvType.text = card.type
        binding.level.text = card.level.toString()
        val image = card.card_images.forEach {
            Picasso.get().load(it.image_url).into(binding.ivCards)
            Log.println(Log.INFO,"Valor Imagen",it.image_url_small)
        }

    }
}