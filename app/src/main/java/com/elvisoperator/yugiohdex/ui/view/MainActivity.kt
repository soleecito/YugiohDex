package com.elvisoperator.yugiohdex.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import com.elvisoperator.yugiohdex.databinding.RecyclerCardFragmentBinding
import com.elvisoperator.yugiohdex.ui.fragments.CardAdapter

import com.elvisoperator.yugiohdex.viewmodel.RecyclerCardViewModel
import com.elvisoperator.yugiohdex.ui.fragments.RecyclerViewModelFactory

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener  {


    private lateinit var bindingActivity: ActivityMainBinding
    private val recyclerCardViewModel: RecyclerCardViewModel by viewModels()
    private lateinit var recyclerbinding: RecyclerCardFragmentBinding
    private lateinit var adapter: CardAdapter
    private lateinit var factory : RecyclerViewModelFactory
    private lateinit var viewModel: RecyclerCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivity.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchDatabase(newText)
        return true
    }

    private fun searchDatabase(newText: String?) {

        val query = "?name=$newText%"


    }


}