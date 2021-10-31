package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.database.DatabaseImpl
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
                /*modificar los datos a llevar*/
                emit(repository.getCardsList("?name=$nameCard%"))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    }


    fun initDatabase(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseImpl.buildDatabase(context)
        }
    }

    fun saveCard(card: BasicCard) {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun deleteCard(card: BasicCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCard(card)
            
        }
    }

}
