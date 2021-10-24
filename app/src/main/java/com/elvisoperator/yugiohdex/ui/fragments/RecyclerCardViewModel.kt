package com.elvisoperator.yugiohdex.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.network.CardApliClient
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository
import com.elvisoperator.yugiohdex.domain.GetCardsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RecyclerCardViewModel : ViewModel() {

    val cardModel = MutableLiveData<List<BasicCard>>()

    val getCardUseCase = GetCardsUseCase()

    fun getCard() {
        viewModelScope.launch {
            val result = getCardUseCase()
            if (!result.isNullOrEmpty()) {
                cardModel.postValue(result!!)
            }
        }

    }


/*
    fun searchName(query :String) {
        viewModelScope.launch {
            val data = repository.searchName(query)
            carts.value = data
        }


    }



 */


}


