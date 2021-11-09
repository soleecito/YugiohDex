package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.properties.Delegates
import android.util.DisplayMetrics
import android.view.Display
import com.google.android.material.internal.ContextUtils.getActivity
import android.util.TypedValue





class AutoFitGridLayoutManager(val context: Context ) : GridLayoutManager(context, 1) {

    private var columnWidthChanged = true
    private var columnWidth = 220
    private var columns = 0
    private val columnHeight = 300



    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State){


        if(columnWidthChanged && columnWidth>0){
            var totalSpace = 0
             if(orientation == VERTICAL){
                 totalSpace =  width - paddingStart - paddingEnd

            }else {
                 totalSpace = height - paddingTop - paddingBottom
            }

            val spanCount = max(2, totalSpace/400)
            setSpanCount(spanCount)
            columnWidthChanged = true
        }
        super.onLayoutChildren(recycler, state)


    }

}