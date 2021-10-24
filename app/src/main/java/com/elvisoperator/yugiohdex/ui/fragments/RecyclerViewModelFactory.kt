package com.elvisoperator.yugiohdex.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository

class RecyclerViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecyclerCardViewModel() as T
    }
}