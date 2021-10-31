package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.base.BaseViewHolder
import com.elvisoperator.yugiohdex.data.CardModel
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.databinding.CardsRowBinding
import com.squareup.picasso.Picasso

class MainAdapter(
    private val context: Context, private var cardList: CardModel,
    val itemClickLister: OnCardClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCardClickListener {
        fun onCardClick(data: Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolder(layoutInflater.inflate(R.layout.cards_row, parent, false))
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cardList.list[position])
        }
    }

    override fun getItemCount(): Int {
        return cardList.list.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Data>(itemView) {
        override fun bind(item: Data) {
            val binding = CardsRowBinding.bind(itemView)
            val image = item.card_images[0]
            Picasso.get().load(image.image_url).into(binding.ivCards)
            Log.println(Log.INFO, "Valor Imagen", image.image_url_small)

            itemView.setOnClickListener { itemClickLister.onCardClick(item) }

        }

    }
}