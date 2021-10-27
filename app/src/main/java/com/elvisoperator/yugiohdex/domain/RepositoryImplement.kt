package com.elvisoperator.yugiohdex.domain

import com.elvisoperator.yugiohdex.data.CardModel
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.vo.Resource

class RepositoryImplement(private val dataSource: DataSource): Repository {
    override suspend fun getAllSpellCardCoroutine(): Resource<CardModel> {
        return dataSource.getAllSpellCardCoroutine()
    }

    override suspend fun getCardsList(cardName : String): Resource<CardModel> {
        return dataSource.getCardName(cardName)
    }

    override suspend fun getCardListFavorites(): Resource<List<BasicCard>> {
        return dataSource.getCardFavorites()
    }

    override suspend fun insertCard(card: BasicCard) {
        dataSource.insertCardIntoRoom(card)
    }


}