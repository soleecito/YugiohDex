package com.elvisoperator.yugiohdex.data.database

import androidx.room.*
import com.elvisoperator.yugiohdex.data.model.BasicCard

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    suspend fun getFavoritesCard(): MutableList<BasicCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cards: BasicCard)

    @Delete
    suspend fun deleteCard(card: BasicCard)
}