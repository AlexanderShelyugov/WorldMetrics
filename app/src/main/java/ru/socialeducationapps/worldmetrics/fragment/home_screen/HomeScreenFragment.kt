package ru.socialeducationapps.worldmetrics.fragment.home_screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToAboutMeActivity

class HomeScreenFragment : Fragment(R.layout.home_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().findViewById<View>(R.id.mb_global_overview)
            .setOnClickListener {
//                findNavController().navigate(actionHomeScreenToGlobalOverview())
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_screen, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.about_me -> {
            findNavController().navigate(actionHomeScreenToAboutMeActivity())
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}