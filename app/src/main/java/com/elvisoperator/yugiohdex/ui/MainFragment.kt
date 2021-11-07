package com.elvisoperator.yugiohdex.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage
import com.elvisoperator.yugiohdex.databinding.FragmentMainBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainAdapter
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource


class MainFragment : Fragment(), MainAdapter.OnCardClickListener, MainAdapter.OnFavoritesClickListener, MainAdapter.ImageFavorites {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepositoryImplement(
                DataSource()
            )
        )
    }
    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var mainAdapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentMainBinding.bind(view)
        viewModel.initDatabase(requireContext())
        setupRecyclerView()
        setupObservers()

    }

    private fun setupObservers() {
        viewModel.fetchCardList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    mainBinding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    mainBinding.progressBar.visibility = View.GONE
                    mainAdapter = MainAdapter(requireContext(), result.data, this, this, this)
                    mainBinding.recyclerViewCard.adapter = mainAdapter

                }
                is Resource.Failure -> {
                    mainBinding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "error occurred while fetching the data",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        val columns = 2
        mainBinding.recyclerViewCard.layoutManager =
            GridLayoutManager(requireContext(), columns)  //LinearLayoutManager(requireContext())
        mainBinding.recyclerViewCard.setHasFixedSize(true)
    }


    private fun setupSearchView(menu: Menu){


        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setCard(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun onCardClick(data: Data) {

        val bundle = Bundle()
        val basicCard = dataToBasicCard(data)
        bundle.putParcelable("card", basicCard)
        findNavController().navigate(R.id.action_mainFragment_to_detailCardFragment, bundle)
    }

    private fun dataToBasicCard(data: Data): BasicCard {
        val image = BasicCardImage(
            id = data.card_images[0].id,
            image_url = data.card_images[0].image_url,
            image_url_small = data.card_images[0].image_url_small
        )
        return BasicCard(
            id = data.id,
            name = data.name,
            type = data.type,
            level = data.level,
            desc = data.desc,
            image = image
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
        setupSearchView(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item, requireView()
            .findNavController()) || super.onOptionsItemSelected(item)

    }

    override fun OnFavoritesClick(data: Data, actualValue: Boolean) {
        if(actualValue){
            viewModel.deleteCard(dataToBasicCard(data))
        } else {
            viewModel.saveCard(dataToBasicCard(data))
        }
    }

    override fun setImageFavorites(data: Data): Boolean {
        val favoritesList = MainViewModel.copy.value?.list ?: emptyList()
        var exists = false
        for(element in favoritesList){
            if(element.id == data.id){
                exists = true
            }
        }
        return exists
    }

}