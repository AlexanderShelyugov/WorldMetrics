package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.KeyValueList
import ru.alexander.worldmetrics.model.KeyValueListDiffCallback

class CountriesListWithIndexViewAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<CountriesListWithIndexViewAdapter.ViewHolder>() {
    private var data: KeyValueList<String> = KeyValueList()

    fun setData(newData: KeyValueList<String>) {
        if (itemCount == 0) {
            if (newData.isEmpty) {
                return
            }
            data = newData
            notifyItemRangeChanged(0, newData.size)
            return
        }
        val diff = calculateDiff(KeyValueListDiffCallback(data, newData))
        data = newData
        diff.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName: TextView = view.findViewById(R.id.tv_country_name)
        val value: TextView = view.findViewById(R.id.tv_value)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_name_with_value_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.run {
            val country = data.keys[position]
            countryName.text = country
            value.text = data[country]
            this.itemView.setOnClickListener {
                onClick.invoke(country)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
