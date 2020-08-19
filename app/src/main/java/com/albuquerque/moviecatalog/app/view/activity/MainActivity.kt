package com.albuquerque.moviecatalog.app.view.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.core.view.activity.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment? ?: return

        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_destination, R.id.search_destination, R.id.favorites_destination))

        with(host.navController) {
            setupActionBar(this, appBarConfiguration)
            setupBottomNavMenu(this)
        }

    }

    private fun setupBottomNavMenu(navController: NavController) {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.apply {
            this.setupWithNavController(navController)
        }
    }

    private fun setupActionBar(navController: NavController, appBarConfig : AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_host).navigateUp(appBarConfiguration)
    }

}