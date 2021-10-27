package com.elvisoperator.yugiohdex.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.database.AppDatabase
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.databinding.FragmentFavoritesBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainAdapterFavorite
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource

class FavoritesFragment : Fragment()  , MainAdapterFavorite.OnCardClickListener{

    private val viewModel by activityViewModels<MainViewModel>{
        VMFactory(RepositoryImplement(DataSource( AppDatabase.getDatabase(requireActivity().applicationContext) )))
    }

    private lateinit var favoriteBinding : FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteBinding = FragmentFavoritesBinding.bind(view)



        setupRecyclerView()
        setupObservers()

    }

    private fun setupObservers() {
        viewModel.getCardFavorites().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val list = result.data.map { card ->
                        BasicCard(card.id , card.name , card.type , card.level , card.image , true)
                    }

                   favoriteBinding.rvFavorite.adapter = MainAdapterFavorite(requireContext(), list ,this )
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setupRecyclerView(){

        favoriteBinding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        favoriteBinding.rvFavorite.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onCardClick(data: BasicCard) {
        TODO("Not yet implemented")
    }

}