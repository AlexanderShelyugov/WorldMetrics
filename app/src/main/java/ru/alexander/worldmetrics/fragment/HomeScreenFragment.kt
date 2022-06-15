package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToCorruptionPerceptionsOverview
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToDemocracyIndexOverview
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToPressFreedomOverview
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.bindNavigation

class HomeScreenFragment : Fragment(R.layout.home_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val v = requireView()
        v.findViewById<ViewGroup>(R.id.ll_indexes).let { container ->
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
            R.string.democracy_index_name to actionHomeScreenToDemocracyIndexOverview(),
            R.string.corruption_perceptions_index_name to actionHomeScreenToCorruptionPerceptionsOverview(),
            R.string.press_freedom_index_name to actionHomeScreenToPressFreedomOverview()
        )
    }
}