package com.elvisoperator.yugiohdex.domain

import com.elvisoperator.yugiohdex.data.CardModel
import com.elvisoperator.yugiohdex.vo.Resource
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CardApliClient {

    
    @GET("?type=Spell%20Card")
    suspend fun getAllSpellCardCoroutine(): CardModel

    @GET
    suspend fun searchName(@Url query: String ): CardModel

    companion object{
        operator fun invoke() : CardApliClient {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
                .build()
                .create(CardApliClient::class.java)
        }
    }

}