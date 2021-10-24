package com.elvisoperator.yugiohdex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage

@Database(
    version = 1,
    entities = [BasicCard::class, BasicCardImage::class]
)

abstract class AppDatabase: RoomDatabase() {

    abstract fun cardDao(): CardDao
}