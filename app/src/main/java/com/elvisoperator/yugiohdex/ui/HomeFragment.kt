package com.elvisoperator.yugiohdex.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.model.CardModel
import com.elvisoperator.yugiohdex.data.model.Data
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.BasicCardImage
import com.elvisoperator.yugiohdex.data.model.DataSearch
import com.elvisoperator.yugiohdex.databinding.FragmentHomeBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.elvisoperator.yugiohdex.vo.Resource
import com.squareup.picasso.Picasso
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
    private var dataSearch : DataSearch = DataSearch("%" , "" , "name" , "")

    private lateinit var cardRecommended: BasicCard
    /*pasar a recycler view*/
    private lateinit var card1 : BasicCard
    private lateinit var card2 : BasicCard
    private lateinit var card3 : BasicCard
    private lateinit var card4 : BasicCard


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
       clickCircleDivine()

        /*implementacio de las cartas*/
        clickCardPrincipal1()
        clickCardPrincipal2()
        clickCardPrincipal3()
        clickCardPrincipal4()

        clickRecommended()

    }

    private fun clickRecommended() {

        homeBinding.imageCardRecommended.setOnClickListener {

            val bundle = Bundle()
            /*parseo card Data a BasicCard*/
            /*Lo guardo a bundle*/
            bundle.putParcelable("card",cardRecommended)
            /*pasar al otro fragment*/
            findNavController().navigate(R.id.detailCardFragment, bundle)

        }
    }

    private fun clickCardPrincipal4() {
        homeBinding.cardPrincipal4.setOnClickListener {
            val bundle = Bundle()
            /*parseo card Data a BasicCard*/
            /*Lo guardo a bundle*/
            bundle.putParcelable("card", card4)
            /*pasar al otro fragment*/
            findNavController().navigate(R.id.detailCardFragment, bundle)
        }
    }

    private fun clickCardPrincipal3() {
        homeBinding.cardPrincipal3.setOnClickListener {
            val bundle = Bundle()
            /*parseo card Data a BasicCard*/
            /*Lo guardo a bundle*/
            bundle.putParcelable("card", card3)
            /*pasar al otro fragment*/
            findNavController().navigate(R.id.detailCardFragment, bundle)
        }
    }

    private fun clickCardPrincipal2() {
        homeBinding.cardPrincipal2.setOnClickListener {
            val bundle = Bundle()
            /*parseo card Data a BasicCard*/
            /*Lo guardo a bundle*/
            bundle.putParcelable("card", card2)
            /*pasar al otro fragment*/
            findNavController().navigate(R.id.detailCardFragment, bundle)
        }
    }

    private fun clickCardPrincipal1() {
        homeBinding.cardPrincipal1.setOnClickListener {
            val bundle = Bundle()
            /*parseo card Data a BasicCard*/
            /*Lo guardo a bundle*/
            bundle.putParcelable("card", card1)
            /*pasar al otro fragment*/
            findNavController().navigate(R.id.detailCardFragment, bundle)
        }
    }

    private fun clickCircleDivine() {
        homeBinding.circleDivine.setOnClickListener {
            Toast.makeText(requireContext() , "tocaste" , Toast.LENGTH_SHORT).show()

            val bundle = Bundle()
            /*parseo card Data a BasicCard*/
            /*Lo guardo a bundle*/
            val dataSearch = DataSearch("%a" , "" , "name" , "d" )
            bundle.putParcelable("clickcard",dataSearch)
            /*pasar al otro fragment*/
            findNavController().navigate(R.id.mainFragment, bundle)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
         super.onCreateOptionsMenu(menu, inflater)

        // setupSearchView(menu)
    }

    private fun setupObservers() {
        viewModel.fetchCardListHome.observe(viewLifecycleOwner) { result ->
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
        }


    }

    private fun dataPass(it: Data): BasicCard {


          val image = BasicCardImage(
              id = it.card_images[0].id,
              image_url = it.card_images[0].image_url,
              image_url_small = it.card_images[0].image_url_small
          )

          val cardDialog = BasicCard(
              id = it.id,
              name = it.name,
              type = it.type,
              level = it.level,
              desc = it.desc,
              image = image
          )

          return cardDialog

    }




    private fun new(data: CardModel) {


        val item1 =  data.list[0]
        val image1 = item1.card_images[0]
        Picasso.get().load(image1.image_url_small).into(homeBinding.cardPrincipal1)
        card1 = dataPass(data.list[0])


        val item2 =  data.list[1]
        val image2 = item2.card_images[0]
        Picasso.get().load(image2.image_url_small).into(homeBinding.cardPrincipal2)
        card2= dataPass(data.list[1])

        val item3 =  data.list[2]
        val image3 = item3.card_images[0]
        Picasso.get().load(image3.image_url_small).into(homeBinding.cardPrincipal3)
        card3 = dataPass(data.list[2])

        val item4 =  data.list[3]
        val image4 = item4.card_images[0]
        Picasso.get().load(image4.image_url_small).into(homeBinding.cardPrincipal4)
        card4 = dataPass(data.list[3])

    }

    private fun recommend(data: CardModel) {
        val position = Random.nextInt(0,100)
        val item =  data.list[position]
        val image = item.card_images[0]
        Picasso.get().load(image.image_url).into(homeBinding.imageCardRecommended)
        cardRecommended = dataPass(data.list[position])
    }

    override fun onDestroyView() {
    viewModel.fetchCardListHome.removeObservers(requireParentFragment().viewLifecycleOwner)
    super.onDestroyView()
    }

}

