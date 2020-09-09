package com.albuquerque.moviecatalog.view.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.albuquerque.moviecatalog.R
import com.albuquerque.core.view.activity.BaseActivity
import com.albuquerque.domain.extensions.setGone
import com.albuquerque.domain.extensions.setVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MovieCatalogNoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment? ?: return

        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_destination, R.id.search_destination, R.id.favorites_destination))

        with(host.navController) {
            setupActionBar(this, appBarConfiguration)
            setupBottomNavMenu(this)

            addOnDestinationChangedListener { _, destination, _ ->

                when(destination.id) {

                    R.id.home_destination -> {
                        supportActionBar?.hide()
                        bottomNavigationView.setVisible()
                    }

                    R.id.movie_detail_destination -> {
                        supportActionBar?.hide()
                        bottomNavigationView.setGone()
                    }

                    else -> {
                        supportActionBar?.show()
                        bottomNavigationView.setVisible()
                    }
                }

            }
        }

    }

    private fun setupBottomNavMenu(navController: NavController) {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.apply {
            this.setupWithNavController(navController)
        }
    }

    private fun setupActionBar(navController: NavController, appBarConfig : AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentHost).navigateUp(appBarConfiguration)
    }

}