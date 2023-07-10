package ru.socialeducationapps.worldmetrics.feature.home.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenFragmentToCountriesComparisonFragment
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToAboutMeActivity
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToGlobalOverview

class HomeScreenFragment : Fragment(R.layout.home_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().apply {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            val backPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isOpen) {
                        drawerLayout.close()
                    }
                }
            }
            drawerLayout.addDrawerListener(HomeScreenDrawerListener(backPressedCallback))
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner, backPressedCallback
            )

            requireActivity().findViewById<MaterialToolbar>(R.id.topToolbar)
                .setNavigationOnClickListener {
                    if (drawerLayout.isOpen) {
                        drawerLayout.close()
                    } else {
                        drawerLayout.open()
                    }
                }

            findViewById<NavigationView>(R.id.navigationDrawerMenu)
                .setNavigationItemSelectedListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_item_global_overview -> findNavController().navigate(
                            actionHomeScreenToGlobalOverview()
                        )

                        R.id.menu_item_compare -> findNavController().navigate(
                            actionHomeScreenFragmentToCountriesComparisonFragment(
                                countryACode = "usa",
                                countryBCode = "che"
                            )
                        )

                        R.id.menu_item_credits -> findNavController().navigate(
                            actionHomeScreenToAboutMeActivity()
                        )
                    }

                    menuItem.isChecked = true
                    drawerLayout.close()
                    true
                }
        }
    }

    private class HomeScreenDrawerListener(private val callback: OnBackPressedCallback) :
        DrawerListener {
        override fun onDrawerOpened(drawerView: View) {
            callback.isEnabled = true
        }

        override fun onDrawerClosed(drawerView: View) {
            callback.isEnabled = false
        }

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            // do nothing
        }

        override fun onDrawerStateChanged(newState: Int) {
            // do nothing
        }
    }
}