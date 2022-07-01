package ru.socialeducationapps.worldmetrics.modules.country_overview.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.CompositeRVAdapter
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getNameByCode
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.rv_adapter.CorruptionPerceptionsAdapterFactory.Companion.getCorruptionPerceptionsFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.country_overview.viewmodel.CountryOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.democracy_index.rv_adapter.DemocracyIndexAdapterFactory.Companion.getDemocracyIndexFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.press_freedom.rv_adapter.PressFreedomAdapterFactory.Companion.getPressFreedomFeaturesAdapter

class CountryOverviewFragment : Fragment(R.layout.country_overview) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: CountryOverviewFragmentArgs by navArgs()
        val countryCode = args.countryCode

        requireView().findViewById<TextView>(R.id.tv_country_name).text =
            getNameByCode(countryCode)
        requireView().findViewById<RecyclerView>(R.id.rv_content).adapter =
            createAdapter(countryCode)
    }

    private fun createAdapter(countryCode: String): Adapter<out ViewHolder> {
        val model: CountryOverviewViewModel by activityViewModels()
        val data = model.getDataForCountry(countryCode)
        val atomicAdapters = listOf(
            getCorruptionPerceptionsFeaturesAdapter().also { it.setData(data.corruptionPerceptions) } as Adapter<ViewHolder>,
            getDemocracyIndexFeaturesAdapter().also { it.setData(data.democracyIndex) } as Adapter<ViewHolder>,
            getPressFreedomFeaturesAdapter().also { it.setData(data.pressFreedom) } as Adapter<ViewHolder>,
        )
        return CompositeRVAdapter(atomicAdapters)
    }
}