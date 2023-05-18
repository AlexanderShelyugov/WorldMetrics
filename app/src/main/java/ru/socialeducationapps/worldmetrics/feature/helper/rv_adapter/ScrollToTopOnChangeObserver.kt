package ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter

import androidx.recyclerview.widget.RecyclerView

class ScrollToTopOnChangeObserver(
    private val recyclerView: RecyclerView
) : RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        recyclerView.scrollToPosition(0)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) = onChanged()

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) =
        onChanged()

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) = onChanged()

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) = onChanged()

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) =
        onChanged()
}