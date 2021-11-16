package com.elvisoperator.yugiohdex.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataSearch  (
        var search  :   String     ,
        var filter  :   String    ,
        var order   :   String    ,
        val filterArchetype  : String ): Parcelable