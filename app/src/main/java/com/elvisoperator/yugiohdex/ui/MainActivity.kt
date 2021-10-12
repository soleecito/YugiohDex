package com.elvisoperator.yugiohdex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.elvisoperator.yugiohdex.models.CardAdapter
import com.elvisoperator.yugiohdex.Data
import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import com.elvisoperator.yugiohdex.ui.fragments.HomeFragment
import com.elvisoperator.yugiohdex.ui.fragments.SpellCardFragment
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter : CardAdapter
    private val listCards = mutableListOf<Data>()
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchCard.setOnQueryTextListener(this)

        drawerLayout = binding.drawerLayout

        onCreateNavigation()
        onClickNavigation()


    }

    private fun onCreateNavigation() {
        val navController = findNavController(R.id.myNavHostFragment)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private fun onClickNavigation() {
        binding.buttonNavigation.setOnClickListener {
            val navController = this.findNavController(R.id.myNavHostFragment)
            NavigationUI.navigateUp(navController, drawerLayout)
        }

    }

   /* override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
*/

    //Reveer si es posible pasarlo a un companion object en la interfaz o bien como clase aparte
    //Para esto cree la clase API, falta implementar los metodos
    private fun  getListCard(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/api/v7/cardinfo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchName(query : String ){

        //clase que funciona como un map
        val bundle = Bundle()
        bundle.putString("nombre" , query)

        val transcaccion = supportFragmentManager.beginTransaction()
        val fragmento = SpellCardFragment()
        fragmento.arguments = bundle
        transcaccion.replace(R.id.myNavHostFragment, fragmento)
        transcaccion.addToBackStack(null)
        transcaccion.commit()

    }

    private fun showError() {
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
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



