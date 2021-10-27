package com.elvisoperator.yugiohdex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elvisoperator.yugiohdex.domain.Repository

class VMFactory(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }

}