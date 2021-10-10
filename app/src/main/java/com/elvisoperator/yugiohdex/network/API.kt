package com.elvisoperator.yugiohdex.network

import com.elvisoperator.yugiohdex.Card
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private fun getAPI(): YugiohAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(YugiohAPI::class.java)
    }

    //falta implementacion
    fun getCards(){

    }


}