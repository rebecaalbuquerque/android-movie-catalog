package com.albuquerque.moviecatalog.app.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.albuquerque.moviecatalog.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()

    }

    private fun setupViews() {
        // Acha o Navigation Controller
        val navigationController = findNavController(R.id.fragmentHost)

        // Configura o Navigation Controller com a BottomNavigationView
        bottomNavigationView.setupWithNavController(navigationController)

    }

    fun showBottomNavigation() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        bottomNavigationView.visibility = View.GONE
    }

}