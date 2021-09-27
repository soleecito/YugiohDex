package com.elvisoperator.yugiohdex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YugiohAPI {

    @GET("cardinfo.php")
    fun getCharacters(): Call<List<Character>>

}