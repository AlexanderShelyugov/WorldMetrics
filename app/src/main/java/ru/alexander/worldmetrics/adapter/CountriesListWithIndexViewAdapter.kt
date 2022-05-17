package ru.alexander.worldmetrics.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.SortedList
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.CustomSortedListPairCallback
import ru.alexander.worldmetrics.model.KeyValueItem

typealias Item = KeyValueItem

class CountriesListWithIndexViewAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private val COMPORATOR_BY_NAME: Comparator<Item> = compareBy { it.k }
        private val COMPARATOR_BY_VALUE: Comparator<Item> = compareBy { it.v }
    }

    private val callback = CustomSortedListPairCallback(this)
        .also { it.comparator = comparator }
    private val data: SortedList<KeyValueItem> = SortedList(Item::class.java, callback)

    private val comparator
        get() = if (sortByCountry) {
            COMPORATOR_BY_NAME.let {
                if (naturalOrder) it else it.reversed()
            }
        } else {
            COMPARATOR_BY_VALUE.let {
                if (naturalOrder) it else it.reversed()
            }
        }

    var sortByCountry = true
        set(value) {
            field = value
            updateComparator()
        }
    var naturalOrder = true
        set(value) {
            field = value
            updateComparator()
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateComparator() {
        callback.comparator = comparator
        notifyDataSetChanged()
    }

    fun setData(newData: Map<String, String>) {
        if (itemCount == 0 && newData.isEmpty()) {
            return
        }
        val newItems = newData.asSequence()
            .map { Item(it.key, it.value) }
            .toMutableList()
        data.replaceAll(newItems)
    }

    class CountryIndexViewHolder(view: View) : ViewHolder(view) {
        val countryName: TextView = view.findViewById(R.id.tv_country_name)
        val value: TextView = view.findViewById(R.id.tv_value)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_name_with_value_item, viewGroup, false)
        return CountryIndexViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        (viewHolder as CountryIndexViewHolder).run {
            val country = data[position].k
            countryName.text = country
            value.text = data[position].v
            this.itemView.setOnClickListener {
                onClick.invoke(country)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size()
}
