package com.elvisoperator.yugiohdex

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface YugiohAPI {

    @GET
   suspend fun getCard(@Url url :String) : Response<Character>

}