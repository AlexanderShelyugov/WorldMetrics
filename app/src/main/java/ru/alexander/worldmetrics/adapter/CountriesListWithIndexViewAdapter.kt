package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.SortedList
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.KeyValueItem

typealias Item = KeyValueItem

class CountriesListWithIndexViewAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private val COMPORATOR_BY_NAME: Comparator<Item> = compareBy { it.k }
        private val COMPARATOR_BY_VALUE: Comparator<Item> = compareBy { it.v }
    }

    //    private val callback = CustomSortedListPairCallback<ViewHolder>(this)
//        .also { it.comparator = calculateComparator() }
    private var data: SortedList<Item> = createData()
    private var comparator = calculateComparator()

    private fun createData(): SortedList<Item> = SortedList(Item::class.java,
        object : SortedList.Callback<Item>() {
            override fun compare(item1: Item, item2: Item): Int {
                return comparator.compare(item1, item2)
            }

            override fun areContentsTheSame(i1: Item, i2: Item): Boolean {
                return i1.v == i2.v
            }

            override fun areItemsTheSame(i1: Item, i2: Item): Boolean {
                return i1.k == i2.k
            }

            override fun onChanged(pos: Int, count: Int) {
                notifyItemRangeChanged(pos, count)
            }

            override fun onInserted(pos: Int, count: Int) {
                notifyItemRangeInserted(pos, count)
            }

            override fun onRemoved(pos: Int, count: Int) {
                notifyItemRangeRemoved(pos, count)
            }

            override fun onMoved(pos: Int, count: Int) {
                notifyItemMoved(pos, count)
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

    var sortByCountry = true
    var naturalOrder = true

    fun reSort() {
        val prevData = mutableListOf<Item>()
        for (i in 0 until data.size()) {
            val item = data.get(i)
            prevData.add(item)
        }
//        callback.comparator = calculateComparator()
        comparator = calculateComparator()
        data = createData().also {
            it.addAll(prevData)
        }
    }

    fun setData(newData: Map<String, String>) {
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
