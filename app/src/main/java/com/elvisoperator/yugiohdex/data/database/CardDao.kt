package com.elvisoperator.yugiohdex.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.elvisoperator.yugiohdex.data.model.BasicCard

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    fun getAll(): List<BasicCard>

    @Insert
    fun insert(vararg cards: BasicCard)

    @Delete
    fun deleteCard(card: BasicCard)
}