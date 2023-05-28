package ru.socialeducationapps.worldmetrics.feature.country.overview.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.CompositeRVAdapter
import ru.socialeducationapps.worldmetrics.feature.country.overview.viewmodel.CountryOverviewViewModel
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.rv_adapter.CorruptionPerceptionsAdapterFactory.Companion.getCorruptionPerceptionsFeaturesAdapter
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.rv_adapter.DemocracyIndexAdapterFactory.Companion.getDemocracyIndexFeaturesAdapter
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.rv_adapter.PressFreedomAdapterFactory.Companion.getPressFreedomFeaturesAdapter
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode

class CountryOverviewFragment : InjectableFragment(R.layout.country_overview) {
    private val model by viewModels<CountryOverviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<CountryOverviewFragmentArgs>()
        val countryCode = args.countryCode
        requireView().run {
            findViewById<TextView>(R.id.tv_country_name)
                .setText(getNameIdByCode(countryCode) ?: R.string.country_name_unknown)
            findViewById<RecyclerView>(R.id.rv_content).adapter =
                createAdapter(countryCode)
        }
    }

    private fun createAdapter(countryCode: String): Adapter<out ViewHolder> {
        val data = model.getDataForCountry(countryCode)
        val atomicAdapters = listOf(
            getCorruptionPerceptionsFeaturesAdapter().also { it.setState()setData(data.corruptionPerceptions) } as Adapter<ViewHolder>,
            getDemocracyIndexFeaturesAdapter().also { it.setData(data.democracyIndex) } as Adapter<ViewHolder>,
            getPressFreedomFeaturesAdapter().also { it.setData(data.pressFreedom) } as Adapter<ViewHolder>,
        )
        return CompositeRVAdapter(atomicAdapters)
    }
}