package com.elvisoperator.yugiohdex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.domain.GetCardsUseCase
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


