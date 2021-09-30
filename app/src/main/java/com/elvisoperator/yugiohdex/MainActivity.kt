package com.elvisoperator.yugiohdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CardAdapter
    private val listCards = mutableListOf<Data>()
    lateinit var toggle: ActionBarDrawerToggle



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchCharacters.setOnQueryTextListener(this)
        val room = appDatabase()
        initReciclerView()
        testReciclerView()
        navigationBar()
    }

    private fun appDatabase() = Room
        .databaseBuilder(this, AppDatabase::class.java, "list_cards")
        .allowMainThreadQueries()
        .build()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigationBar() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {


            when (it.itemId) {

                R.id.nav_home -> Toast.makeText(applicationContext, "Click Home", Toast.LENGTH_LONG)
                    .show()
                R.id.nav_myDesk -> Toast.makeText(
                    applicationContext,
                    "Click My desk",
                    Toast.LENGTH_LONG
                ).show()
                R.id.nav_favorite -> Toast.makeText(
                    applicationContext,
                    "Click Favorite",
                    Toast.LENGTH_LONG
                ).show()
                R.id.nav_settings -> Toast.makeText(
                    applicationContext,
                    "Click Settings",
                    Toast.LENGTH_LONG
                ).show()
                R.id.nav_login -> Toast.makeText(
                    applicationContext,
                    "Click Login",
                    Toast.LENGTH_LONG
                ).show()

            }

            true
        }
    }

    private fun testReciclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getListCards().create(YugiohAPI::class.java)
                .getData("?format=Speed%20Duel")
            val cards = call.body()
            runOnUiThread {
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

    private fun initReciclerView() {
        binding.recyclerViewCharacter.layoutManager = LinearLayoutManager(this)
        adapter = CardAdapter(listCards)
        binding.recyclerViewCharacter.adapter = adapter

    }

    private fun getListCards(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getListCards().create(YugiohAPI::class.java).getData("?name=$query%")
            val cards = call.body()
            runOnUiThread {
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
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}



