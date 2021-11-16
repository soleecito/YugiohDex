package com.elvisoperator.yugiohdex.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.database.DatabaseImpl
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardModel
import com.elvisoperator.yugiohdex.data.model.DataSearch
import com.elvisoperator.yugiohdex.domain.Repository
import com.elvisoperator.yugiohdex.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: Repository) : ViewModel() {


    private var cardData = MutableLiveData<DataSearch>()

    companion object Favorites {
        var copy = MutableLiveData<BasicCardModel>()
    }

    fun setCard(cardName: DataSearch) {
        cardData.value = cardName
    }

    /*  fun setCard(cardName: String) {
        cardData.value = cardName
    }*/

    init {
        val init = DataSearch("%" ,"" ,"name" , "")
        setCard(init)
    }

    /* init {
        setCard(init)
    }*/

    val fetchCardList = cardData.switchMap{
        liveData(Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                //it.order = "name"

                emit(repository.getCardsList(it))
                Log.d("Estado", it.search)
                //emit(repository.getCardsList("?name=$nameCard.search"))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    }

    val fetchCardListHome = cardData.switchMap{
        liveData(Dispatchers.IO) {
            emit(Resource.Loading)
            try {
               it.order = "New"
                emit(repository.getCardsList(it))
                Log.d("Estado", it.search)
                //emit(repository.getCardsList("?name=$nameCard.search"))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    }




    fun initDatabase(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseImpl.buildDatabase(context)
            loadFavorites()
        }
    }

    fun saveCard(card: BasicCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCard(card)
            loadFavorites()
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

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getCardModelFavorites()
            copy.postValue(list)
        }
    }

    fun deleteCard(card: BasicCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCard(card)
            loadFavorites()
        }
    }

}
