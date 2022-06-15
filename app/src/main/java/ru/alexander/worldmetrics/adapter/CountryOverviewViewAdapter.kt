package ru.alexander.worldmetrics.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.CORRUPTION_PERCEPTIONS_HEADER
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.CORRUPTION_PERCEPTIONS_ITEM
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.COUNTRY_BADGE
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.DEMOCRACY_INDEX_HEADER
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.DEMOCRACY_INDEX_ITEM
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.PRESS_FREEDOM_HEADER
import ru.alexander.worldmetrics.adapter.CountryOverviewViewAdapter.ViewTypes.PRESS_FREEDOM_ITEM
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData
import ru.alexander.worldmetrics.modules.democracy_index.model.DemocracyIndexData
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData
import ru.alexander.worldmetrics.viewmodel.country_overview.CountryOverviewData

class CountryOverviewViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var data: CountryOverviewData

    fun setData(data: CountryOverviewData) {
        this.data = data
    }

    class MyViewHolder(
        val lcv: View
    ) : RecyclerView.ViewHolder(lcv)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh: ViewHolder
        when (viewType) {
            COUNTRY_BADGE.ordinal -> {

            }
            CORRUPTION_PERCEPTIONS_HEADER.ordinal -> {

            }
            CORRUPTION_PERCEPTIONS_ITEM.ordinal -> {

            }
            DEMOCRACY_INDEX_HEADER.ordinal -> {

            }
            DEMOCRACY_INDEX_ITEM.ordinal -> {

            }
            PRESS_FREEDOM_HEADER.ordinal -> {

            }
            PRESS_FREEDOM_ITEM.ordinal -> {

            }
            else -> {
                throw Error("Unexpected view type")
            }
        }
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        // We will show a country badge, then
        // Every feature of indexes + header for specific index
        return ITEM_TYPE_LAYOUT.asSequence()
            .map { it.first }
            .sum()
    }

    override fun getItemViewType(position: Int): Int {
        var pos = position
        for (detector in ITEM_TYPE_LAYOUT) {
            pos -= detector.first
            if (pos <= 0) return detector.second.ordinal
        }
        return -1
    }

    private companion object {
        val ITEM_TYPE_LAYOUT = listOf(
            1 to COUNTRY_BADGE,
            1 to CORRUPTION_PERCEPTIONS_HEADER,
            CorruptionPerceptionsData.FEATURES_NUMBER to CORRUPTION_PERCEPTIONS_ITEM,
            1 to DEMOCRACY_INDEX_HEADER,
            DemocracyIndexData.FEATURES_NUMBER to DEMOCRACY_INDEX_ITEM,
            1 to PRESS_FREEDOM_HEADER,
            PressFreedomData.FEATURES_NUMBER to PRESS_FREEDOM_ITEM,
        )
    }

    private enum class ViewTypes {
        COUNTRY_BADGE,
        CORRUPTION_PERCEPTIONS_HEADER,
        CORRUPTION_PERCEPTIONS_ITEM,
        DEMOCRACY_INDEX_HEADER,
        DEMOCRACY_INDEX_ITEM,
        PRESS_FREEDOM_HEADER,
        PRESS_FREEDOM_ITEM,
    }
}