package com.elvisoperator.yugiohdex.domain

import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository

class GetCardsUseCase {

    private val repository = RecyclerRepository()

    suspend operator fun invoke(): List<BasicCard>?{
        return repository.getCard()
    }
}