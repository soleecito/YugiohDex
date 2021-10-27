package com.elvisoperator.yugiohdex.ui.viewmodel

import androidx.lifecycle.*
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.domain.Repository
import com.elvisoperator.yugiohdex.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: Repository) : ViewModel() {


    private val cardData = MutableLiveData<String>()

    fun setCard(cardName: String) {
        cardData.value = cardName
    }

    init {
        setCard("a%")
    }

    val fetchCardList = cardData.distinctUntilChanged().switchMap { nameCard ->

        liveData(Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(repository.getCardsList("?name=$nameCard%"))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    }

    fun saveCard(card: BasicCard) {
        viewModelScope.launch {
            repository.insertCard(card)
        }
    }

    fun getCardFavorites() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repository.getCardListFavorites())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}
