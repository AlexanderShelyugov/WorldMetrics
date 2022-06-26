package ru.alexander.worldmetrics.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class FilterableAdapter<DataItem> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var originalData: List<DataItem> = emptyList()
    private var query = ""
    private val differ = AsyncListDiffer(this, getDiffCallBack())

    protected val data: List<DataItem>
        get() = differ.currentList

    fun searchWith(query: String) {
        this.query = query.trim()
        filterDataIfPossible()
    }

    fun setData(data: List<DataItem>) {
        originalData = data.toList() // Make a copy
        filterDataIfPossible()
    }

    private fun filterDataIfPossible() {
        if (query.isEmpty()) {
            differ.submitList(originalData.toList())
            notifyItemRangeChanged(0, originalData.size)
            return
        }
        val result = originalData.asSequence()
            // Not just .filter, because
            // We want to order data by it first query occurrence
            .map {
                search(query, it) to it
            }
            .filter { 0 <= it.first }
            .sortedBy { it.first }
            .map { it.second }
            .toList()
        differ.submitList(result)
    }

    final override fun getItemCount(): Int = differ.currentList.size
    protected abstract fun getDiffCallBack(): DiffUtil.ItemCallback<DataItem>
    protected abstract fun search(query: String, item: DataItem): Int
}