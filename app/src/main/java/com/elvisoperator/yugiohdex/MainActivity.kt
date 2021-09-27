package com.elvisoperator.yugiohdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var service: YugiohAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerViewCharacter.layoutManager = LinearLayoutManager(this)

        getListCharacter()
    }

    private fun  getListCharacter() {
        API().getCharacters(object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.isSuccessful) {
                    val personajes = response.body()
                    //seteamos el adapter
                    binding.recyclerViewCharacter.adapter = CharacterAdapter(personajes!!)
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }

        })
    }
}