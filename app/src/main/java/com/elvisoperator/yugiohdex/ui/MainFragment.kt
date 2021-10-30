package com.elvisoperator.yugiohdex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.Data
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.databinding.FragmentMainBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainAdapter
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource


class MainFragment : Fragment(), MainAdapter.OnCardClickListener {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepositoryImplement(
                DataSource()
            )
        )
    }
    private lateinit var adapter: MainAdapter
    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var mainAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentMainBinding.bind(view)

        viewModel.initDatabase(requireContext())
        setupRecyclerView()
        setupSearchView()
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
                    mainBinding.recyclerViewCard.adapter =
                        MainAdapter(requireContext(), result.data, this)
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

    private fun setupSearchView() {
        mainBinding.searchCards.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        bundle.putParcelable("card", data)
        findNavController().navigate(R.id.action_mainFragment_to_detailCardFragment, bundle)
    }
}