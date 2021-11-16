package com.elvisoperator.yugiohdex.ui

import android.content.ContentValues
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.*

import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.elvisoperator.yugiohdex.MainActivity

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage
import com.elvisoperator.yugiohdex.data.model.DataSearch
import com.elvisoperator.yugiohdex.databinding.FragmentMainBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.AutoFitGridLayoutManager
import com.elvisoperator.yugiohdex.ui.viewmodel.MainAdapter
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource


class MainFragment : Fragment(),RadioGroup.OnCheckedChangeListener, MainAdapter.OnCardClickListener, MainAdapter.OnFavoritesClickListener, MainAdapter.ImageFavorites {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepositoryImplement(
                DataSource()
            )
        )
    }


    private lateinit var mainBinding: FragmentMainBinding
    private var dataSearch : DataSearch = DataSearch("%" , "" , "name", "")
    private lateinit var mainAdapter: MainAdapter
     lateinit var sQuery : String
    private var dataSearchHome : DataSearch = DataSearch("%" , "" , "name" , "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireArguments().let {
            dataSearch = it.getParcelable("clickcard")!!
            viewModel.setCard(dataSearch)
            viewModel.initDatabase(requireContext())
            setupObservers()
        }
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentMainBinding.bind(view)

        viewModel.initDatabase(requireContext())



        /*configuración de recycler view*/
        setupRecyclerView()

        /*conviguración de obvervación de la inicialización y cambio del view model*/
        setupObservers()


        /*inicio de check group*/
        mainBinding.rgFilter.setOnCheckedChangeListener(this)
        mainBinding.rgOrder.setOnCheckedChangeListener(this)

        /*button filter*/
        clickBtFilter()
        clickBtCloseFilter()
        clickFilterApply()

        /*button order*/
        clickBtOrder()
        clickOrderApply()
        clickBtCloseOrder()


    }





    private fun setupObservers() {
        viewModel.fetchCardList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    mainBinding.progressBar.visibility = VISIBLE
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
        mainBinding.recyclerViewCard.layoutManager =
            AutoFitGridLayoutManager(requireContext())
        mainBinding.recyclerViewCard.setHasFixedSize(true)
    }

    private fun setupSearchView(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                dataSearch.search = query!!
                Log.d("Busqueda", dataSearch.search)
                viewModel.setCard(dataSearch)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                /*if(!newText.isNullOrEmpty()) {
                    dataSearch.search = newText!!
                    viewModel.setCard(dataSearch)
                } else
                    setupObservers()*/
                return false
            }

        })

    }

    override fun onCardClick(data: Data) {
        /*crear clase*/
        val bundle = Bundle()

        /*parseo card Data a BasicCard*/
        val basicCard = dataToBasicCard(data)

        /*Lo guardo a bundle*/
        bundle.putParcelable("card", basicCard)

        /*pasar al otro fragment*/
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

        return NavigationUI.onNavDestinationSelected(
            item, requireView()
                .findNavController()
        ) || super.onOptionsItemSelected(item)

    }

    override fun OnFavoritesClick(data: Data, actualValue: Boolean) {
        if (actualValue) {
            viewModel.deleteCard(dataToBasicCard(data))
        } else {
            viewModel.saveCard(dataToBasicCard(data))
        }
    }

    override fun setImageFavorites(data: Data): Boolean {
        val favoritesList = MainViewModel.copy.value?.list ?: emptyList()
        var exists = false
        for (element in favoritesList) {
            if (element.id == data.id) {
                exists = true
            }
        }
        return exists
    }



    //seleccionador de radioGroup
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {


        when(checkedId){
            mainBinding.rBtnEffectMonster.id -> {
                dataSearch.filter = "Effect Monster"
                Toast.makeText(requireContext() , "pase" , Toast.LENGTH_SHORT).show()
            }
            mainBinding.rBtnMagic.id -> {
                dataSearch.filter = "Spell Card"
            }
            mainBinding.rBtnNormalMonster.id -> {
                dataSearch.filter = "Normal Monster"
            }
            mainBinding.rBtnTramp.id -> {
                dataSearch.filter = "Trap Card"
            }

        }

        when(checkedId){
            mainBinding.rBtnName.id -> {
                dataSearch.order = "Name"

            }
            mainBinding.rBtnType.id -> {
                dataSearch.order = "Type"

            }
            mainBinding.rBtnRelevance.id -> {
                dataSearch.order = "New"
            }

        }


    }

    private fun clickBtCloseFilter(){
        mainBinding.btCloseFilter.setOnClickListener {
            mainBinding.filter.visibility = INVISIBLE

        }
    }

    private fun clickFilterApply(){
        mainBinding.btnApply.setOnClickListener {
            viewModel.setCard(dataSearch)
            mainBinding.filter.visibility = INVISIBLE
        }
    }

    /*boton de filtro para abrir el dialogo y activar los filtros*/
    private fun clickBtFilter(){
        mainBinding.btFilter.setOnClickListener {
            openFilterDialog()
        }
    }


    /*apertura de openDialog*/
    private fun  openFilterDialog(){
        mainBinding.filter.visibility = VISIBLE
        mainBinding.order.visibility = INVISIBLE
    }

    /*boton de order para abrir el dialogo y activar los filtros*/
    private fun clickBtOrder(){
        mainBinding.btOrder.setOnClickListener {
            openOrderDialog()
        }
    }

    /*apertura de openDialog*/
    private fun  openOrderDialog(){
        mainBinding.filter.visibility = INVISIBLE
        mainBinding.order.visibility = VISIBLE
    }


    private fun clickBtCloseOrder(){
        mainBinding.btCloseOrder.setOnClickListener {
            mainBinding.order.visibility = INVISIBLE
        }
    }

    private fun clickOrderApply(){
        mainBinding.btnApplyOrder.setOnClickListener {
            viewModel.setCard(dataSearch)
            mainBinding.order.visibility = INVISIBLE
        }
    }




}



