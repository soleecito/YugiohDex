package com.elvisoperator.yugiohdex

import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private fun getAPI(): YugiohAPI {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://db.ygoprodeck.com/api/v7/")
            .build();
        return retrofit.create(YugiohAPI::class.java)
    }

    fun getCharacters(callback: Callback<List<Character>>){
        getAPI().getCharacters().enqueue(callback)
    }

}