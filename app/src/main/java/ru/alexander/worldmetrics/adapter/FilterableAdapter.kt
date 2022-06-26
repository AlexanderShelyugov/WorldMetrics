package ru.alexander.worldmetrics.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class FilterableAdapter<DataItem> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    private fun refreshData() {
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
        differ.submitList(result)
    }

    protected val data: List<DataItem>
        get() = differ.currentList

    final override fun getItemCount(): Int = differ.currentList.size
    protected abstract fun getDiffCallBack(): DiffUtil.ItemCallback<DataItem>
    protected abstract fun search(query: String, item: DataItem): Int
}