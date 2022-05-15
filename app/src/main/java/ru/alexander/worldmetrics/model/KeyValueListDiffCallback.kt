package ru.alexander.worldmetrics.model

import androidx.recyclerview.widget.DiffUtil

class KeyValueListDiffCallback(
    private val dataFrom: KeyValueList<String>,
    private val dataTo: KeyValueList<String>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = dataFrom.size
    override fun getNewListSize(): Int = dataTo.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        dataFrom.keys[oldItemPosition] == dataTo.keys[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        dataFrom[oldItemPosition] == dataTo[newItemPosition]
}
