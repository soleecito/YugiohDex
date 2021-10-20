package com.elvisoperator.yugiohdex.data.repository

import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.network.CardApliClient

class RecyclerRepository(
    val api: CardApliClient
)  {

    suspend fun getCard(): CardModel {
        return api.getAllSpellCardCoroutine()
    }

    suspend fun searchName(query :String): CardModel {
        return api.searchName(query)
    }
}