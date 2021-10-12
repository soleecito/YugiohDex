package com.elvisoperator.yugiohdex.network

import com.elvisoperator.yugiohdex.Card
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class API {

     fun getAPI(): YugiohAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(YugiohAPI::class.java)
    }

    private fun  getListCard(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //falta implementacion
    suspend fun getCards(query :String) : Response<Card> {
        return getListCard(). create(YugiohAPI::class.java).getCards("?name=$query%")

    }


}