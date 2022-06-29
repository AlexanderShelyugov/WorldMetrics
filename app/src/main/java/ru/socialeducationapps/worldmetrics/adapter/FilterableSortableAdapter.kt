package ru.socialeducationapps.worldmetrics.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class FilterableSortableAdapter<DataItem> : Adapter<ViewHolder>() {

    private var originalData: List<DataItem> = emptyList()
    private var query = ""
    private val differ = AsyncListDiffer(this, getDiffCallBack())

    fun searchWith(query: String) {
        this.query = query.trim()
        refresh()
    }

    fun setData(data: List<DataItem>) {
        originalData = data.toList() // Make a copy
        refresh()
    }

    fun refresh() {
        if (query.isEmpty()) {
            differ.submitList(sort(originalData))
            return
        }
        originalData.asSequence()
            // Not just .filter, because
            // We want to order data by its first query occurrence
            .map {
                search(query, it) to it
            }
            .filter { 0 <= it.first }
            .sortedBy { it.first }
            .map { it.second }
            .toList()
            .run(differ::submitList)
    }

    private fun sort(items: List<DataItem>): List<DataItem> =
        calculateComparator()?.run(items::sortedWith)
            ?: items

    protected val data: List<DataItem>
        get() = differ.currentList

    protected open fun calculateComparator(): Comparator<DataItem>? = null
    protected open fun search(query: String, item: DataItem) = 0
    final override fun getItemCount(): Int = differ.currentList.size
    protected abstract fun getDiffCallBack(): ItemCallback<DataItem>
}