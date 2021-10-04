package com.elvisoperator.yugiohdex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvisoperator.yugiohdex.databinding.FragmentCardListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardListFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var adapter : CardAdapter
    lateinit var binding: FragmentCardListBinding
    private val listCards = mutableListOf<Data>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCardListBinding>(inflater,
            R.layout.fragment_card_list,container,false)
        return binding.root

        binding.searchCard.setOnQueryTextListener(this)

        initReciclerView()
        testReciclerView()



    }

    private fun initReciclerView() {
        binding.recyclerViewCard.layoutManager = LinearLayoutManager(context)
        adapter = CardAdapter(listCards)
        binding.recyclerViewCard.adapter = adapter

    }

    private fun testReciclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            val call =getListCard().create(YugiohAPI::class.java).getCard("?format=Speed%20Duel")
            val cards = call.body()
            activity?.runOnUiThread {
                if(call.isSuccessful){
                    val actualCards = cards?.list ?: emptyList()
                    listCards.clear()
                    listCards.addAll(actualCards)
                    adapter.notifyDataSetChanged()

                }else{
                    showError()
                }
            }

        }
    }

    private fun  getListCard(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun searchName(query : String ){
        CoroutineScope(Dispatchers.IO).launch {
            val call =getListCard().create(YugiohAPI::class.java).getCard("?name=$query%")
            val cards = call.body()
            activity?.runOnUiThread {
                if(call.isSuccessful){
                    val actualCards = cards?.list ?: emptyList()
                    listCards.clear()
                    listCards.addAll(actualCards)
                    adapter.notifyDataSetChanged()

                }else{
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}




