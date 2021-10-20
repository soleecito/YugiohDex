package com.elvisoperator.yugiohdex.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository

class RecyclerViewModelFactory (private val repository: RecyclerRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecyclerCardViewModel(repository) as T
    }
}