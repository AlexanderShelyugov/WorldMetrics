package ru.alexander.worldmetrics.model

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedListAdapterCallback

typealias T = KeyValueItem

class CustomSortedListPairCallback<H> constructor(
    adapter: RecyclerView.Adapter<H>
) : SortedListAdapterCallback<T>(adapter) where H : RecyclerView.ViewHolder {
    var comparator: Comparator<T> = compareBy { it.toString() }

    override fun compare(o1: T, o2: T): Int = comparator.compare(o1, o2)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.v == newItem.v

    override fun areItemsTheSame(item1: T, item2: T): Boolean = item1.k == item2.k
}