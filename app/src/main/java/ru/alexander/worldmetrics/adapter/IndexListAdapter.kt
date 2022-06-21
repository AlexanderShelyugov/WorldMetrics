package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R

class IndexListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: List<IndexListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.index_for_country_list_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        data[position].let {
            (holder as VH).label.text = it.name
            holder.itemView.setOnClickListener(it.onClick)
        }
    }

    override fun getItemCount(): Int = data.size

    data class IndexListItem(val name: String, val onClick: View.OnClickListener)

    private class VH(v: View) : RecyclerView.ViewHolder(v) {
        val label = v.findViewById<TextView>(R.id.tv_index_name)!!
    }
}