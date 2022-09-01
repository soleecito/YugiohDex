package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.R

import com.elvisoperator.yugiohdex.base.BaseViewHolderFavorite
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.databinding.CardsRowFavoriteBinding


import com.squareup.picasso.Picasso

class MainAdapterFavorite(
    private val context: Context, private var cardList: List<BasicCard>,
    val itemClickLister: OnCardClickListener,
    val itemClose: OnDeleteItem
) : RecyclerView.Adapter<BaseViewHolderFavorite<*>>() {


    interface OnCardClickListener {
        fun onCardClick(data: BasicCard, position: Int)
    }

    interface OnDeleteItem {
        fun onCloseClick(data: BasicCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderFavorite<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolderFavorite(layoutInflater.inflate(R.layout.cards_row_favorite, parent, false))
    }


    override fun getItemCount(): Int {
        return cardList.size
    }

    inner class MainViewHolderFavorite(itemView: View) :
        BaseViewHolderFavorite<BasicCard>(itemView) {
        override fun bind(item: BasicCard, position: Int) {
            val binding = CardsRowFavoriteBinding.bind(itemView)
            binding.tvName.text = item.name
            binding.tvType.text = item.type
            binding.level.text = item.level.toString()
            Picasso.get().load(item.image.image_url).into(binding.ivCards)
            Log.println(Log.INFO, "Valor Imagen", item.image.image_url)
            itemView.setOnClickListener { itemClickLister.onCardClick(item, position) }
            binding.closeFavorite.setOnClickListener{ itemClose.onCloseClick(item) }
        }

    }




    override fun onBindViewHolder(holder: BaseViewHolderFavorite<*>, position: Int) {

        when (holder) {
            is MainViewHolderFavorite -> holder.bind(cardList[position], position)
        }
    }
}