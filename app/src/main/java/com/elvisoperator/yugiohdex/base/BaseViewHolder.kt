package com.elvisoperator.yugiohdex.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.data.model.Data

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Data)
}