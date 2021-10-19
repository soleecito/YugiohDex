package com.elvisoperator.yugiohdex.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvisoperator.yugiohdex.Data
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.models.CardAdapter
import com.elvisoperator.yugiohdex.network.YugiohAPI
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.elvisoperator.yugiohdex.*

import com.elvisoperator.yugiohdex.databinding.FragmentSpellCardBinding
import com.elvisoperator.yugiohdex.network.API

class SpellCardFragment : Fragment() {

    companion object {
        fun newInstance() = SpellCardFragment()
    }

    lateinit var adapter : CardAdapter
    private val listCards = mutableListOf<Data>()

    private lateinit var viewModel: CardViewModel
    lateinit var binding: FragmentSpellCardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spell_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         binding = FragmentSpellCardBinding.bind(view)

        initReciclerView()
        if (arguments != null) {
            val nombre : String? = arguments?.getString("nombre")
            if (nombre != null) {
                testReciclerView(nombre)
            }
        }
       // testReciclerView("dark")

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardViewModel::class.java)
        // TODO: Use the ViewModel


    }




  fun testReciclerView(query: String) {


      viewLifecycleOwner.lifecycleScope.launch {
          val call = getListCard().create(YugiohAPI::class.java).getCards("?name=$query%")
          val cards = call.body()
          activity?.runOnUiThread {
              if (call.isSuccessful) {
                  val actualCards = cards?.list ?: emptyList()
                  listCards.clear()
                  listCards.addAll(actualCards)
                  adapter.notifyDataSetChanged()

              } else {
                  showError()
              }
          }

      }
  }

    private fun showError() {
        Toast.makeText(activity,"Error", Toast.LENGTH_LONG).show()
    }

    private fun initReciclerView() {
        binding.recyclerViewCard.layoutManager = LinearLayoutManager(activity)
        adapter = CardAdapter(listCards)
        binding.recyclerViewCard.adapter = adapter

    }

    //Reveer si es posible pasarlo a un companion object en la interfaz o bien como clase aparte
    //Cree la clase API, le falta implementar metodo
    private fun  getListCard(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}