package com.elvisoperator.yugiohdex.data.database

import androidx.room.*
import com.elvisoperator.yugiohdex.data.model.BasicCard

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    fun getFavoritesCard(): MutableList<BasicCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg cards: BasicCard)

    @Delete
    fun deleteCard(card: BasicCard)
}