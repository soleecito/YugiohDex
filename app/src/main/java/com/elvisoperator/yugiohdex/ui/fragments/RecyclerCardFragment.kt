package com.elvisoperator.yugiohdex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvisoperator.yugiohdex.R

import com.elvisoperator.yugiohdex.data.model.Data
import com.elvisoperator.yugiohdex.data.network.CardApliClient
import com.elvisoperator.yugiohdex.data.repository.RecyclerRepository

import com.elvisoperator.yugiohdex.databinding.RecyclerCardFragmentBinding
import com.elvisoperator.yugiohdex.ui.fragments.RecyclerViewModelFactory


class RecyclerCardFragment : Fragment() , RecyclerViewItemClick {


    private lateinit var factory : RecyclerViewModelFactory
    private lateinit var viewModel: RecyclerCardViewModel
    lateinit var binding: RecyclerCardFragmentBinding
    private lateinit var adapter: CardAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_card_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RecyclerCardFragmentBinding.bind(view)

        adapter = CardAdapter(){}

        val api = CardApliClient()
        val repository = RecyclerRepository(api)
        factory = RecyclerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(RecyclerCardViewModel::class.java)


        binding.recyclerViewCard.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCard.setHasFixedSize(true)
        binding.recyclerViewCard.adapter = adapter

        viewModel.carts.observe(viewLifecycleOwner, Observer { carts->
            adapter.card = carts.list
            adapter.notifyDataSetChanged()
        })

        viewModel.getCard()
    }


    override  fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

    override fun onRecyclerViewItemClick(view: View, card: Data) {
        when(view.id) {
            R.id.buttonFavorite -> {
                Toast.makeText(requireContext(), "Book Button Clicked", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun buscar(query : String){

    }
}


/*

 private lateinit var factory: MoviesViewModelFactory
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = MoviesApi()
        val repository = MoviesRepository(api)

        factory = MoviesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)

        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MoviesAdapter(movies, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, movie: Movie) {
        when(view.id){
            R.id.button_book -> {
                Toast.makeText(requireContext(), "Book Button Clicked",Toast.LENGTH_LONG).show()
            }
            R.id.layout_like ->{
                Toast.makeText(requireContext(), "Like Layout Clicked",Toast.LENGTH_LONG).show()
            }
        }
    }
 */
