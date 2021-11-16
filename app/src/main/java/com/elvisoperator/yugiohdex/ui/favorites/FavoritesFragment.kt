package com.elvisoperator.yugiohdex.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.database.CardDao
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardModel
import com.elvisoperator.yugiohdex.databinding.FragmentFavoritesBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainAdapterFavorite
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment(), MainAdapterFavorite.OnCardClickListener, MainAdapterFavorite.OnDeleteItem {


    /*@Inject
    lateinit var cardDao : CardDao*/

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepositoryImplement(DataSource()))
    }

    private lateinit var favoriteBinding: FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listObserver = Observer<BasicCardModel> { newList ->
            favoriteBinding.rvFavorite.adapter = MainAdapterFavorite(requireContext(), newList.list, this, this)
        }
        MainViewModel.copy.observe(this, listObserver)
        viewModel.loadFavorites()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteBinding = FragmentFavoritesBinding.bind(view)

        setupRecyclerView()
        viewModel.loadFavorites()
    }

    private fun setupRecyclerView() {

        favoriteBinding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())

        favoriteBinding.rvFavorite.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        favoriteBinding.rvFavorite.setHasFixedSize(true)

    }


    override fun onCardClick(data: BasicCard, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("card", data)
        findNavController().navigate(R.id.action_favoriteFragment_to_detailCardFragment, bundle)
    }

    override fun onCloseClick(data: BasicCard) {
        viewModel.deleteCard(data)
    }

}