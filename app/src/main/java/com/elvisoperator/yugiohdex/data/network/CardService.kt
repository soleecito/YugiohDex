package com.elvisoperator.yugiohdex.data.network

import com.elvisoperator.yugiohdex.core.RetrofitHelper
import com.elvisoperator.yugiohdex.data.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCards() : CardModel? {

        return withContext(Dispatchers.IO){
            val response = retrofit.create(CardApliClient::class.java).getAllSpellCard()
            response.body()
        }

    }
}