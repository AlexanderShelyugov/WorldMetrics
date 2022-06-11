package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.CountriesData
import ru.alexander.worldmetrics.model.TripleItem
import ru.alexander.worldmetrics.search.SearchCriteria
import ru.alexander.worldmetrics.search.TextSearchCriteria

typealias Item = TripleItem

class CountriesListWithIndexViewAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {

    companion object {
        private val COMPORATOR_BY_NAME: Comparator<Item> = compareBy { it.second }
        private val COMPARATOR_BY_VALUE: Comparator<Item> = compareBy { it.third }
    }

    var sortByCountry = true
    var naturalOrder = true
    val searchCriteria: SearchCriteria<Item> = TextSearchCriteria()

    private var data: SortedList<Item> = createData()
    private var fullData: List<Item> = listOf()
    private var comparator = calculateComparator()

    private fun createData(): SortedList<Item> = SortedList(Item::class.java,
        object : SortedListAdapterCallback<Item>(this) {
            override fun compare(item1: Item, item2: Item): Int {
                return comparator.compare(item1, item2)
            }

            override fun areContentsTheSame(i1: Item, i2: Item): Boolean {
                return i1.third == i2.third
            }

            override fun areItemsTheSame(i1: Item, i2: Item): Boolean {
                return i1.second == i2.second
            }
        })

    private fun calculateComparator(): Comparator<Item> {
        return if (sortByCountry) {
            COMPORATOR_BY_NAME.let {
                if (naturalOrder) it else it.reversed()
            }
        } else {
            COMPARATOR_BY_VALUE.let {
                if (naturalOrder) it else it.reversed()
            }
        }
    }

    fun reSort() {
        val prevData = (0 until data.size())
            .map { i -> data.get(i) }
            .toMutableList()
        comparator = calculateComparator()
        setData(prevData)
    }

    fun setData(newData: Map<String, String>) {
        val newItems = newData.asSequence()
            .map { Item(it.key, CountriesData.getNameByCode(it.key), it.value) }
            .toMutableList()
        setData(newItems)
    }

    private fun setData(newData: List<Item>) {
        fullData = newData.toList()
        data = createData().also {
            it.addAll(newData)
        }
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
            data[position].let { row ->
                val countryCode = row.first
                countryName.text = row.second
                value.text = row.third
                this.itemView.setOnClickListener {
                    onClick.invoke(countryCode)
                }
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size()
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val search = constraint?.toString()?.lowercase() ?: ""
                val filteredData: List<Item> = searchCriteria.search(fullData, Item::second, search)
                return FilterResults().apply { values = filteredData }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                data = createData().also {
                    it.addAll(results?.values as List<Item> ?: emptyList())
                }
                notifyItemRangeChanged(0, data.size())
            }
        }
    }


}
