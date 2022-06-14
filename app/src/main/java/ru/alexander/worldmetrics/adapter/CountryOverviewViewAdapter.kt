package ru.alexander.worldmetrics.adapter

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
import ru.alexander.worldmetrics.viewmodel.country_overview.CountryOverviewData

class CountryOverviewViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var data: CountryOverviewData

    fun setData(data: CountryOverviewData) {
        this.data = data
    }

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
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
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