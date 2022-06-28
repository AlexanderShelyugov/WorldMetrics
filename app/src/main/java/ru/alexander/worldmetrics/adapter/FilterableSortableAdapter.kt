package ru.alexander.worldmetrics.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class FilterableSortableAdapter<DataItem> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var originalData: List<DataItem> = emptyList()
    private var query = ""
    private val differ = AsyncListDiffer(this, getDiffCallBack())

    fun searchWith(query: String) {
        this.query = query.trim()
        refreshData()
    }

    fun setData(data: List<DataItem>) {
        originalData = data.toList() // Make a copy
        refreshData()
    }

    protected fun refreshData() {
        if (query.isEmpty()) {
            differ.submitList(originalData.toList())
            return
        }
        val result = originalData.asSequence()
            // Not just .filter, because
            // We want to order data by its first query occurrence
            .map {
                search(query, it) to it
            }
            .filter { 0 <= it.first }
            .sortedBy { it.first }
            .map { it.second }
            .toList()
        differ.submitList(sort(result))
    }

    private fun sort(items: List<DataItem>): List<DataItem> {
        val comparator = calculateComparator() ?: return items
        return items.sortedWith(comparator)
    }

    protected val data: List<DataItem>
        get() = differ.currentList

    open fun calculateComparator(): Comparator<DataItem>? = null
    final override fun getItemCount(): Int = differ.currentList.size
    protected abstract fun getDiffCallBack(): DiffUtil.ItemCallback<DataItem>
    protected abstract fun search(query: String, item: DataItem): Int
}