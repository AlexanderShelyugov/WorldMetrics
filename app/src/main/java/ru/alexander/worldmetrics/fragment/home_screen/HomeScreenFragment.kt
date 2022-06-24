package ru.alexander.worldmetrics.fragment.home_screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToAboutMeActivity
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo

class HomeScreenFragment : Fragment(R.layout.home_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_screen, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.about_me -> {
            navigateTo(findNavController(), actionHomeScreenToAboutMeActivity())
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}