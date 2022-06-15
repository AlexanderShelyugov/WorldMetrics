package ru.alexander.worldmetrics.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

private typealias AdapterAlias = Adapter<ViewHolder>

/**
 * RecyclerView's adapter that contains other adapters.
 *
 * Only single-typed adapters are supported.
 */
class CompositeRVAdapter(
    private val adapters: List<AdapterAlias>
) : AdapterAlias() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        adapters[viewType].onCreateViewHolder(parent, 0)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val itemsBefore = getItemsBefore(viewType)
        return adapters[viewType].onBindViewHolder(holder, position - itemsBefore)
    }

    private fun getItemsBefore(viewType: Int): Int {
        require(viewType >= 0)
        if (viewType == 0) return 0
        var sum = 0
        for (adapterIndex in 0 until viewType) {
            sum += adapters[adapterIndex].itemCount
        }
        return sum
    }

    override fun getItemViewType(position: Int): Int {
        var pos = position
        for (adapterIndex in adapters.indices) {
            pos -= adapters[adapterIndex].itemCount
            if (pos < 0) return adapterIndex
        }
        return -1
    }

    override fun getItemCount(): Int {
        val itemsCount = adapters.asSequence()
            .map { it.itemCount }
            .sum()
        Log.i("CompositeRVAdapter", "!!!! We have $itemsCount items")
        return itemsCount
    }
}