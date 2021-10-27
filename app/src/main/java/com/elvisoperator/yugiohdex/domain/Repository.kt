package com.elvisoperator.yugiohdex.domain

import com.elvisoperator.yugiohdex.data.CardModel
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.vo.Resource

interface Repository {

    suspend fun getAllSpellCardCoroutine() : Resource<CardModel>
    suspend fun getCardsList(cardName : String) : Resource<CardModel>

    /*DAO*/
    suspend fun getCardListFavorites() :Resource<List<BasicCard>>
    suspend fun insertCard(card: BasicCard)
}