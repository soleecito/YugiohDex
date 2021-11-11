package com.elvisoperator.yugiohdex

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.elvisoperator.yugiohdex.UserApplication.Companion.prefs
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.viewmodel.AutoFitGridLayoutManager
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.navView.setNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> {

                }
                R.id.searchFragment -> {

                }
                R.id.favoriteCardsFragment -> {

                }
                R.id.profileFragment -> {

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        binding.drawerLayout.user_name.text = prefs.getName()
        binding.drawerLayout.name_deck.text = prefs.getDeckName()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }



}