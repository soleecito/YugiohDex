package com.elvisoperator.yugiohdex.network

import com.elvisoperator.yugiohdex.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface YugiohAPI {

    @GET
   suspend fun getCards(@Url url :String) : Response<Card>

}