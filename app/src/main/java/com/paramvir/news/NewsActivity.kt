package com.paramvir.news

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paramvir.news.sources.PreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main landing activity which contains all the three tabs for Headlines, Sources and Saved.
 */
@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    var newsSources = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news)
        newsSources = PreferencesHelper.getSelectedSources(this).toMutableList()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        newsSources = PreferencesHelper.getSelectedSources(this).toMutableList()
    }

    override fun onStop() {
        super.onStop()
        PreferencesHelper.saveSelectedSources(this, newsSources)
    }

}