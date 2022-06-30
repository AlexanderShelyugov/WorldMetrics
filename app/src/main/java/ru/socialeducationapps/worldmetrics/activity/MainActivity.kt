package ru.socialeducationapps.worldmetrics.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_nav_host_container) as NavHostFragment
        navigationController = navHostFragment.navController
    }

    override fun onResume() {
        super.onResume()
        navigationController.addOnDestinationChangedListener(onFragmentChangeListener)
    }

    override fun onPause() {
        super.onPause()
        navigationController.removeOnDestinationChangedListener(onFragmentChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> navigationController.navigateUp()
        else -> super.onOptionsItemSelected(item)
    }

    private val onFragmentChangeListener = NavController.OnDestinationChangedListener { _, _, _ ->
        supportActionBar?.setDisplayHomeAsUpEnabled(navigationController.previousBackStackEntry != null)
    }
}