package com.elvisoperator.yugiohdex.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}