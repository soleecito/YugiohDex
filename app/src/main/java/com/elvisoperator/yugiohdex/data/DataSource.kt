package com.elvisoperator.yugiohdex.data

import android.util.Log
import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.database.DatabaseImpl
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardModel
import com.elvisoperator.yugiohdex.data.model.CardProvider
import com.elvisoperator.yugiohdex.data.model.DataSearch
import com.elvisoperator.yugiohdex.domain.CardApliClient
import com.elvisoperator.yugiohdex.vo.Resource

class DataSource (){

   suspend fun getCardName(cardName : DataSearch) :Resource<CardModel>{
      var cadena : String = ""
      var estado : Boolean = false
      val name = cardName.search
      val filter = cardName.filter
      val order = cardName.order

      Log.d("name" , cardName.search)
      if(name.isNotEmpty()){
         cadena = "?name=$name%"
         estado = true
      }

      if (estado){
         cadena += "&"
         estado = false
      }

      if (filter.isNotEmpty()){
         cadena += "type=$filter"
         estado = true
      }

      Log.d("filter" , cardName.filter)
      if (estado){
         cadena += "&"
      }

      if (order.isNotBlank()){
         cadena += "sort=$order"
      }
      Log.d("order" , cardName.order)

      return Resource.Success(CardApliClient.invoke().searchName(cadena))
   }

   suspend fun getAllSpellCardCoroutine() :Resource<CardModel>{
      return if(CardProvider.allCards.isNullOrEmpty()) {
         Resource.Success(CardApliClient.invoke().getAllSpellCardCoroutine())
      } else {
         Resource.Success(CardModel(CardProvider.allCards.toMutableList()))
      }
   }


   /*DAO*/
   suspend fun insertCardIntoRoom(card : BasicCard){
      DatabaseImpl.database.cardDao().insert(card)
   }

   suspend fun getCardFavorites(): Resource<MutableList<BasicCard>> {
      return Resource.Success(DatabaseImpl.database.cardDao().getFavoritesCard())
   }

   suspend fun deleteCardIntoRoom(card: BasicCard) {
        DatabaseImpl.database.cardDao().deleteCard(card)
    }

   fun getBasicCardModelFavorites(): BasicCardModel {
      val favorites = DatabaseImpl.database.cardDao().getFavoritesCard()
      return BasicCardModel(favorites)
   }


}