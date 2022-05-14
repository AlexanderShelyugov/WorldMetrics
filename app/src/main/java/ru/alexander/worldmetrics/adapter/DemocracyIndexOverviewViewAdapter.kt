package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R

class DemocracyIndexOverviewViewAdapter(private val dataSet: Map<String, String>) :
    RecyclerView.Adapter<DemocracyIndexOverviewViewAdapter.ViewHolder>() {

    val countries: List<String> = dataSet.keys.toList().sorted()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName: TextView
        val value: TextView

        init {
            countryName = view.findViewById(R.id.tv_di_country_name)
            value = view.findViewById(R.id.tv_di_value)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.democracy_index_overview_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val country = countries[position]
        viewHolder.countryName.text = country
        viewHolder.value.text = dataSet[country]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = countries.size
}