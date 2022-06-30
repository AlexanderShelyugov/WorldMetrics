package ru.socialeducationapps.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.socialeducationapps.worldmetrics.R

private typealias DataItem = CountryListAdapterItem

class CountriesListAdapter : FilterableSortableAdapter<DataItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.countries_list_item, parent, false)
        return CountriesListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as CountriesListViewHolder).let { vh ->
            data[position].let { dataItem ->
                vh.label.text = dataItem.name
                vh.itemView.setOnClickListener { dataItem.onClick(dataItem.iso3Code) }
            }
        }
    }

    private class CountriesListViewHolder(
        view: View,
        val label: TextView = view.findViewById(R.id.tv_country_name)
    ) : ViewHolder(view)

    override fun getDiffCallBack(): ItemCallback<DataItem> = object : ItemCallback<DataItem>() {
        override fun areItemsTheSame(i1: DataItem, i2: DataItem) = i1.iso3Code == i2.iso3Code
        override fun areContentsTheSame(i1: DataItem, i2: DataItem) = i1 == i2
    }

    override fun search(query: String, item: DataItem) =
        item.name.indexOf(query, ignoreCase = true).takeIf { 0 <= it }
            ?: item.iso3Code.indexOf(query, ignoreCase = true).takeIf { 0 <= it }
                ?.let { it + itemCount }
            ?: -1
}

data class CountryListAdapterItem(
    val iso3Code: String,
    val name: String,
    val onClick: (String) -> Unit,
)
