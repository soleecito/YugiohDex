package com.elvisoperator.yugiohdex.data.repository

import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage
import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.model.CardProvider
import com.elvisoperator.yugiohdex.data.network.CardApliClient

class RecyclerRepository {

    private val api = CardApliClient()

    suspend fun getCard(): List<BasicCard> {
        val response = api.getAllSpellCardCoroutine()
        var listCard = emptyList<BasicCard>().toMutableList()
        for(element in response.list){
            val image = BasicCardImage(
                id = element.card_images[0].id,
                image_url = element.card_images[0].image_url,
                image_url_small = element.card_images[0].image_url_small
            )
            val new = BasicCard(
                id = element.id,
                name = element.name,
                type = element.type,
                level = element.level.toString(),
                image = image
            )
            listCard.add(new)
        }
        CardProvider.cards = listCard.toList()
        return listCard.toList()
    }

    suspend fun searchName(query :String): CardModel {
        return api.searchName(query)
    }
}