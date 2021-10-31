package com.elvisoperator.yugiohdex.data

import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.database.DatabaseImpl
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.CardProvider
import com.elvisoperator.yugiohdex.domain.CardApliClient
import com.elvisoperator.yugiohdex.vo.Resource

class DataSource (){

   suspend fun getCardName(cardName : String) :Resource<CardModel>{
      return Resource.Success(CardApliClient.invoke().searchName(cardName))
   }

   suspend fun getAllSpellCardCoroutine() :Resource<CardModel>{
      return if(CardProvider.allCards.isNullOrEmpty()) {
         Resource.Success(CardApliClient.invoke().getAllSpellCardCoroutine())
      } else {
         Resource.Success(CardModel(CardProvider.allCards.toMutableList()))
      }
   }


   /*DAO*/
   fun insertCardIntoRoom(card : BasicCard){
      DatabaseImpl.database.cardDao().insert(card)
   }

   fun getCardFavorites(): Resource<List<BasicCard>> {
      return Resource.Success(DatabaseImpl.database.cardDao().getFavoritesCard())
   }

   suspend fun deleteCardIntoRoom(card: BasicCard) {
        DatabaseImpl.database.cardDao().deleteCard(card)
    }


}