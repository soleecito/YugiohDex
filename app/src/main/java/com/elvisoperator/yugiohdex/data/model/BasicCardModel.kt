package com.elvisoperator.yugiohdex.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class BasicCardModel(
    var list: List<BasicCard>
)

@Parcelize
@Entity(tableName = "cards")
data class BasicCard(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "level") var level: Int,
    @Embedded var image: BasicCardImage,
    @ColumnInfo var desc: String,
    @ColumnInfo(name = "fav") var fav: Boolean = false
): Parcelable

@Parcelize
@Entity(tableName = "image")
data class BasicCardImage(
    @PrimaryKey @ColumnInfo(name = "img_id") var id: Int,
    @ColumnInfo(name = "image_url") var image_url: String,
    @ColumnInfo(name = "image_url_small") var image_url_small: String
): Parcelable
