package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.base.BaseViewHolder
import com.elvisoperator.yugiohdex.base.BaseViewHolderFavorite
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.databinding.CardsRowFavoriteBinding
import com.squareup.picasso.Picasso

class MainAdapterFavorite(private val context: Context, private var cardList: List<BasicCard>,
                             val itemClickLister: OnCardClickListener
) : RecyclerView.Adapter<BaseViewHolderFavorite<*>>(){

    interface OnCardClickListener{
        fun onCardClick(data: BasicCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderFavorite<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolderFavorite(layoutInflater.inflate(R.layout.cards_row, parent, false))
    }


    override fun getItemCount(): Int {
       return cardList.size
    }

    inner class MainViewHolderFavorite (itemView : View):BaseViewHolderFavorite<BasicCard>(itemView){
        override fun bind(item: BasicCard) {
            val binding  = CardsRowFavoriteBinding.bind(itemView)

            binding.tvName.text = item.name
            binding.tvType.text = item.type
            binding.level.text = item.level.toString()
                Picasso.get().load(item.image).into(binding.ivCards)
                Log.println(Log.INFO,"Valor Imagen",item.image)

            itemView.setOnClickListener { itemClickLister.onCardClick(item) }
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolderFavorite<*>, position: Int) {
        when(holder){
            is MainViewHolderFavorite ->holder.bind(cardList[position])
        }
    }
}