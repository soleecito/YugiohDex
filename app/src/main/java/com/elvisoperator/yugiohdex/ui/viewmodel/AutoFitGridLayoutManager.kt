package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class AutoFitGridLayoutManager(val context: Context) : GridLayoutManager(context, 1) {

    private var columnWidthChanged = true
    private val columnWidth = 500



    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State){
        if(columnWidthChanged && columnWidth>0){
            var totalSpace = 0
            if(orientation == VERTICAL){
                totalSpace = width - paddingStart - paddingEnd
            }else {
                totalSpace = height - paddingTop - paddingBottom
            }
            val spanCount = max(1, totalSpace/columnWidth)
            setSpanCount(spanCount)
            columnWidthChanged = false
        }
        super.onLayoutChildren(recycler, state)
    }

}