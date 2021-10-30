package com.elvisoperator.yugiohdex.data

import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.domain.CardApliClient
import com.elvisoperator.yugiohdex.vo.Resource

class DataSource (private val appDatabase: AppDatabase){



   suspend fun getCardName(cardName : String) :Resource<CardModel>{
      return Resource.Success(CardApliClient.invoke().searchName(cardName))
   }

   suspend fun getAllSpellCardCoroutine() :Resource<CardModel>{
      return Resource.Success(CardApliClient.invoke().getAllSpellCardCoroutine())
   }


   /*DAO*/
    suspend fun insertCardIntoRoom(card : BasicCard){
      appDatabase.cardDao().insert(card)
   }

   suspend fun getCardFavorites(): Resource<MutableList<BasicCard>> {
      return Resource.Success(appDatabase.cardDao().getFavoritesCard())
   }

   suspend fun deleteCardIntoRoom(card: BasicCard) {
        appDatabase.cardDao().deleteCard(card)
    }


}