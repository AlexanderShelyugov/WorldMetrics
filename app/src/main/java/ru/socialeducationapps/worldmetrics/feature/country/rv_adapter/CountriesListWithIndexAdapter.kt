package ru.socialeducationapps.worldmetrics.feature.country.rv_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.FilterableSortableAdapter
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.ColorGradeCalculator
import kotlin.Float.Companion.NEGATIVE_INFINITY

private typealias Item = CountriesListWithIndexDataItem

class CountriesListWithIndexAdapter(private val onClick: (View, String) -> Unit) :
    FilterableSortableAdapter<Item>() {

    private companion object {
        val COMPARATOR_BY_NAME: Comparator<Item> = compareBy { it.name }
        val COMPARATOR_BY_VALUE: Comparator<Item> =
            compareBy { it.rate.takeIf { x -> x.isFinite() } ?: NEGATIVE_INFINITY }
        val ITEM_CALLBACK = object : ItemCallback<Item>() {
            override fun areItemsTheSame(i1: Item, i2: Item) = i1.iso3Code == i2.iso3Code
            override fun areContentsTheSame(i1: Item, i2: Item) = i1 == i2
        }
    }

    var sortByCountry = true
    var naturalOrder = true
    private var colorCalculator: ColorGradeCalculator? = null
    private var valuesRange: FeatureRange? = null

    fun setValuesRange(valueRange: FeatureRange) {
        valuesRange = valueRange
    }

    fun setColorsRange(colorRange: Pair<Int, Int>) {
        colorCalculator = ColorGradeCalculator(colorRange)
    }

    override fun calculateComparator(): Comparator<Item> {
        return if (sortByCountry) {
            COMPARATOR_BY_NAME.let {
                if (naturalOrder) it else it.reversed()
            }
        } else {
            COMPARATOR_BY_VALUE.let {
                if (naturalOrder) it else it.reversed()
            }
        }
    }

    override fun search(query: String, item: Item) =
        item.name.indexOf(query, ignoreCase = true).takeIf { 0 <= it }
            ?: item.iso3Code.indexOf(query, ignoreCase = true).takeIf { 0 <= it }
                ?.let { it + itemCount }
            ?: -1

    private class CountryIndexViewHolder(
        view: View,
        val countryName: TextView = view.findViewById(R.id.tv_country_name),
        val value: TextView = view.findViewById(R.id.tv_value),
    ) : ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_name_with_value_item, viewGroup, false)
        return CountryIndexViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        (viewHolder as CountryIndexViewHolder).run {
            data[position].let { row ->
                countryName.apply {
                    text = row.name.takeIf { it.isNotBlank() } ?: row.iso3Code
                    transitionName =
                        context.getString(
                            R.string.transition_name_pattern_country_name,
                            row.iso3Code
                        )
                }
                itemView.apply {
                    transitionName =
                        context.getString(R.string.transition_name_pattern_list_item, row.iso3Code)
                    setOnClickListener {
                        onClick(itemView, row.iso3Code)
                    }
                }
                val indexValue = row.rate.takeIf { it.isFinite() }
                val valueText: String
                val valueColor: Int
                val ctx = value.context
                if (indexValue == null) {
                    valueText = ctx.getString(R.string.no_data)
                    valueColor = ctx.getColor(R.color.colorOnPrimary)
                } else {
                    valueText = indexValue.toBigDecimal().toPlainString()
                    valueColor = valuesRange
                        ?.let { range ->
                            colorCalculator?.evalColor(range.first, range.second, row.rate)
                        } ?: R.color.colorOnPrimary
                }
                value.text = valueText
                value.setTextColor(valueColor)
            }
        }
    }

    override fun getDiffCallBack(): ItemCallback<Item> = ITEM_CALLBACK
}

data class CountriesListWithIndexDataItem(
    val iso3Code: String,
    val name: String,
    val rate: Float,
)