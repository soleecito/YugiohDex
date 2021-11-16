package com.elvisoperator.yugiohdex

import android.content.Intent
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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.elvisoperator.yugiohdex.UserApplication.Companion.prefs
import com.elvisoperator.yugiohdex.data.DataSource
import com.elvisoperator.yugiohdex.data.database.CardDao
import com.elvisoperator.yugiohdex.databinding.ActivityMainBinding
import com.elvisoperator.yugiohdex.domain.RepositoryImplement
import com.elvisoperator.yugiohdex.ui.favorites.FavoritesFragment
import com.elvisoperator.yugiohdex.ui.viewmodel.AutoFitGridLayoutManager
import com.elvisoperator.yugiohdex.ui.viewmodel.MainViewModel
import com.elvisoperator.yugiohdex.ui.viewmodel.VMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.synthetic.main.nav_header.view.*
import javax.inject.Inject



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
        val navBar = binding.bottomNavigation
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.mainFragment, R.id.favorites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navBar.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        binding.drawerLayout.user_name.text = prefs.getName()
        binding.drawerLayout.name_deck.text = prefs.getDeckName()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }



}