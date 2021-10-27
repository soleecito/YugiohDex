package com.elvisoperator.yugiohdex.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.data.model.BasicCard

abstract class BaseViewHolderFavorite<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: BasicCard)
}