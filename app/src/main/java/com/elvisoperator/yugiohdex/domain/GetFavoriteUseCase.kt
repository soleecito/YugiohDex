package com.elvisoperator.yugiohdex.domain

import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository

class GetFavoriteUseCase {

    private val repository = RecyclerRepository()

    suspend operator fun invoke(): List<BasicCard>?{
        return repository.getFavorites()
    }
}