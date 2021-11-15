package com.elvisoperator.yugiohdex.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.CardModel
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.model.DataSearch
import com.elvisoperator.yugiohdex.databinding.FragmentHomeBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : Fragment() {


    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepositoryImplement(
                DataSource()
            )
        )
    }

    private lateinit var cardList: CardModel
    private lateinit var homeBinding: FragmentHomeBinding
    private var dataSearch : DataSearch = DataSearch("%" , "" , "name")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.bind(view)
        viewModel.initDatabase(requireContext())
        setupObservers()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
         super.onCreateOptionsMenu(menu, inflater)

        // setupSearchView(menu)
    }

    private fun setupObservers() {
        viewModel.fetchCardListHome.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    homeBinding.constraintHome.visibility = View.INVISIBLE
                    homeBinding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    homeBinding.progressBar.visibility = View.GONE
                    homeBinding.constraintHome.visibility = View.VISIBLE
                    recommend(result.data)
                    new(result.data)
                }
                is Resource.Failure -> {
                    homeBinding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "error occurred while fetching the data",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })


    }

    private fun new(data: CardModel) {


        val item1 =  data.list[0]
        val image1 = item1.card_images[0]
        Picasso.get().load(image1.image_url_small).into(homeBinding.cardPrincipal1)

        val item2 =  data.list[1]
        val image2 = item2.card_images[0]
        Picasso.get().load(image2.image_url_small).into(homeBinding.cardPrincipal2)

        val item3 =  data.list[2]
        val image3 = item3.card_images[0]
        Picasso.get().load(image3.image_url_small).into(homeBinding.cardPrincipal3)

        val item4 =  data.list[3]
        val image4 = item4.card_images[0]
        Picasso.get().load(image4.image_url_small).into(homeBinding.cardPrincipal4)

    }

    private fun recommend(data: CardModel) {
        val position = Random.nextInt(0,100)
        val item =  data.list[position]
        val image = item.card_images[0]
        Picasso.get().load(image.image_url).into(homeBinding.imageCardRecommended)
    }

}