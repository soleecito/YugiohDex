package com.elvisoperator.yugiohdex

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromDataImages(image: List<String>): CardSet{
        return CardSet(null, image[1], image[0], image[4], image[2], image[3])
    }
}