package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.ContextAccess.Companion.context

class CountriesListAdapter : FilterableAdapter<CountryListAdapterItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.countries_list_item, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as VH
        val ctx = vh.itemView.context
        data[position].let { dataItem ->
            vh.label.text = ctx.getString(dataItem.name)
            vh.itemView.setOnClickListener { dataItem.onClick(it, dataItem.iso3Code) }
        }
    }

    private class VH(view: View) : RecyclerView.ViewHolder(view) {
        val label = view.findViewById<TextView>(R.id.tv_country_name)!!
    }

    override fun getDiffCallBack(): DiffUtil.ItemCallback<CountryListAdapterItem> =
        object : DiffUtil.ItemCallback<CountryListAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: CountryListAdapterItem,
                newItem: CountryListAdapterItem
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: CountryListAdapterItem,
                newItem: CountryListAdapterItem
            ): Boolean = oldItem.iso3Code == newItem.iso3Code
        }

    override fun search(query: String, item: CountryListAdapterItem): Int {
        val name = context.getString(item.name)
        return name.indexOf(query, ignoreCase = true)
            .takeIf { 0 <= it }
            ?: item.iso3Code.indexOf(query, ignoreCase = true)
    }
}

data class CountryListAdapterItem(
    val iso3Code: String,
    val name: Int,
    val onClick: (View, String) -> Unit,
)
