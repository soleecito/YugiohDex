package com.elvisoperator.yugiohdex.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage


@Database(entities = [BasicCard::class, BasicCardImage::class],version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context : Context) : AppDatabase{

            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext , AppDatabase::class.java , "cards").allowMainThreadQueries().build()

            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }



}