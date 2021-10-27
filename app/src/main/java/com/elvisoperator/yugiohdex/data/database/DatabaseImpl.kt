package com.elvisoperator.yugiohdex.data.database

import android.content.Context
import androidx.room.Room

class DatabaseImpl {

    companion object {
        lateinit var database: AppDatabase

        fun buildDatabase(context: Context) {
            database = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "favorite_database"
            )
                .allowMainThreadQueries()
                .build()
        }


    }
}