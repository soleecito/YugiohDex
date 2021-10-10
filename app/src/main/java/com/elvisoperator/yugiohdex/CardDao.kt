package com.elvisoperator.yugiohdex

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface CardDao {

    @Query("SELECT * FROM data")
    fun getAll(): List<Data>

    @Update
    fun updateData(vararg data: Data)

    @Transaction
    @Query("SELECT * FROM data")
    fun getDataWithCardImage(): List<DataWithCardImage>
}