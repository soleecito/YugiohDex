package com.elvisoperator.yugiohdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationBar()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigationBar() {
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {


            when(it.itemId){

                R.id.nav_home -> Toast.makeText(applicationContext, "Click Home" , Toast.LENGTH_LONG).show()
                R.id.nav_myDesk -> Toast.makeText(applicationContext, "Click My desk" , Toast.LENGTH_LONG).show()
                R.id.nav_favorite -> Toast.makeText(applicationContext, "Click Favorite" , Toast.LENGTH_LONG).show()
                R.id.nav_settings -> Toast.makeText(applicationContext, "Click Settings" , Toast.LENGTH_LONG).show()
                R.id.nav_login -> Toast.makeText(applicationContext, "Click Login" , Toast.LENGTH_LONG).show()

            }

            true
        }
    }

}



