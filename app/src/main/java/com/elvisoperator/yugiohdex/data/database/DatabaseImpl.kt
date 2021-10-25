package com.elvisoperator.yugiohdex.data.database

import android.content.Context
import androidx.room.Room

class DatabaseImpl {

    companion object{
        fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "favorite_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}