package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.indexes.FeatureRange
import ru.alexander.worldmetrics.view.ColorGradeCalculator

private typealias Item = CountriesListWithIndexDataItem

class CountriesListWithIndexAdapter(private val onClick: (String) -> Unit) :
    FilterableSortableAdapter<Item>() {

    private companion object {
        val COMPARATOR_BY_NAME: Comparator<Item> = compareBy { it.name }
        val COMPARATOR_BY_VALUE: Comparator<Item> = compareBy { it.rate }
        val ITEM_CALLBACK = object : ItemCallback<Item>() {
            override fun areContentsTheSame(i1: Item, i2: Item) = i1 == i2
            override fun areItemsTheSame(i1: Item, i2: Item) = i1.iso3Code == i2.iso3Code
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
                countryName.text = row.name
                itemView.setOnClickListener {
                    onClick.invoke(row.iso3Code)
                }
                if (row.rate.isNaN()) {
                    value.text = ""
                    return
                }
                value.text = row.rate.toString()
                valuesRange?.let { range ->
                    colorCalculator?.evalColor(range.first, range.second, row.rate)
                        ?.run(value::setTextColor)
                }
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