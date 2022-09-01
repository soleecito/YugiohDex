package com.elvisoperator.yugiohdex.domain

import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardModel
import com.elvisoperator.yugiohdex.data.model.DataSearch
import com.elvisoperator.yugiohdex.vo.Resource

interface Repository {

    suspend fun getAllSpellCardCoroutine() : Resource<CardModel>
   // suspend fun getCardsList(cardName : String) : Resource<CardModel>
   suspend fun getCardsList(data : DataSearch) : Resource<CardModel>

    /*DAO*/
    suspend fun getCardListFavorites() :Resource<MutableList<BasicCard>>
    suspend fun insertCard(card: BasicCard)
    suspend fun deleteCard(card: BasicCard)
    suspend fun getCardModelFavorites(): BasicCardModel

}