package ru.alexander.worldmetrics.fragment.home_screen

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.IndexListAdapter
import ru.alexander.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToCorruptionPerceptionsCountryDetail
import ru.alexander.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToDemocracyIndexCountryDetail
import ru.alexander.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToPressFreedomCountryDetail
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.alexander.worldmetrics.viewmodel.CurrentCountryViewModel

class IndexesForCountryFragment : Fragment(R.layout.indexes_for_country) {
    private lateinit var contentContainer: ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: CurrentCountryViewModel by activityViewModels()
        contentContainer = requireView().findViewById(R.id.ll_indexes)
        model.currentCountryCode.observe(viewLifecycleOwner) { countryCode ->
            contentContainer.removeAllViews()
            if (countryCode == null || countryCode.isBlank()) {
                return@observe
            }
            addIndexGroup(
                R.string.index_group_name_political,
                createPoliticalIndexesGroup(countryCode)
            )
        }
    }

    private fun addIndexGroup(groupName: Int, groupContent: List<Pair<Int, NavDirections>>) {
        val groupHeader: TextView = layoutInflater.inflate(
            R.layout.indexes_for_country_index_group_header,
            contentContainer,
            false
        ) as TextView
        groupHeader.setText(groupName)
        contentContainer.addView(groupHeader)
        val indexesList =
            layoutInflater.inflate(R.layout.list_indexes_for_country, contentContainer, false)
                    as RecyclerView
        val adapter = IndexListAdapter()

        adapter.data = groupContent.asSequence()
            .map { groupItem ->
                IndexListAdapter.IndexListItem(
                    getString(groupItem.first)
                ) { navigateTo(it.findNavController(), groupItem.second) }
            }
            .toList()
        indexesList.adapter = adapter

        contentContainer.addView(indexesList)
    }

    private fun createPoliticalIndexesGroup(countryCode: String): List<Pair<Int, NavDirections>> =
        listOf(
            R.string.corruption_perceptions_index_name to actionHomeScreenToCorruptionPerceptionsCountryDetail(
                countryCode
            ),
            R.string.democracy_index_name to actionHomeScreenToDemocracyIndexCountryDetail(
                countryCode
            ),
            R.string.press_freedom_index_name to actionHomeScreenToPressFreedomCountryDetail(
                countryCode
            )
        )

}