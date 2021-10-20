package com.elvisoperator.yugiohdex.ui.fragments

import android.view.View
import com.elvisoperator.yugiohdex.data.model.Data

interface RecyclerViewItemClick {
    fun onRecyclerViewItemClick(view: View, card: Data)
}