package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenFragmentToCorruptionPerceptionsOverviewFragment
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenFragmentToDemocracyIndexOverviewFragment
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenFragmentToPressFreedomOverviewFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.bindNavigation

class HomeScreenFragment : Fragment(R.layout.home_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireView().findViewById<ViewGroup>(R.id.ll_indexes).let { container ->
            INDEXES.forEach { index ->
                val button =
                    layoutInflater.inflate(R.layout.button_default, container, false) as Button
                button.text = getString(index.first)
                bindNavigation(button, index.second)
                container.addView(button)
            }
        }
    }

    private companion object {
        val INDEXES = listOf(
            R.string.democracy_index_name to actionHomeScreenFragmentToDemocracyIndexOverviewFragment(),
            R.string.corruption_perceptions_index_name to actionHomeScreenFragmentToCorruptionPerceptionsOverviewFragment(),
            R.string.press_freedom_index_name to actionHomeScreenFragmentToPressFreedomOverviewFragment()
        )
    }
}