package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R

class CountriesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: List<CountryListAdapterItem> = emptyList()

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

    override fun getItemCount() = data.size

    private class VH(view: View) : RecyclerView.ViewHolder(view) {
        val label = view.findViewById<TextView>(R.id.tv_country_name)!!
    }
}

data class CountryListAdapterItem(
    val iso3Code: String,
    val name: Int,
    val onClick: (View, String) -> Unit,
)
