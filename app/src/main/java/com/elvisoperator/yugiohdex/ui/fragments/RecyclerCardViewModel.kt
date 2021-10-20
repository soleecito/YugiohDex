package com.elvisoperator.yugiohdex.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.network.CardApliClient
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RecyclerCardViewModel( private val repository: RecyclerRepository): ViewModel(){

      val  carts = MutableLiveData<CardModel>()

    fun getCard() {
            viewModelScope.launch {
                val data = repository.getCard()
                carts.value = data
            }

    }



    fun searchName(query :String) {
        viewModelScope.launch {
            val data = repository.searchName(query)
            carts.value = data
        }


    }




}


