package ru.socialeducationapps.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.global.ColorAccess


class IndexListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: List<IndexListItem> = emptyList()
    var itemColor: Int = R.color.white_transparent_25
    var textColor: Int = R.color.colorOnPrimary

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val ctx = parent.context
        val view = LayoutInflater.from(ctx)
            .inflate(R.layout.index_for_country_list_item, parent, false)
        view.background?.let {
            val color = ctx.getColor(itemColor)
            ColorAccess.setColorFilter(it, color)
        }
        return VH(view).also {
            it.label.setTextColor(ctx.getColor(textColor))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        data[position].let {
            (holder as VH).label.text = it.name
            holder.itemView.setOnClickListener(it.onClick)
        }
    }

    override fun getItemCount() = data.size

    data class IndexListItem(val name: String, val onClick: View.OnClickListener)

    private class VH(v: View) : RecyclerView.ViewHolder(v) {
        val label = v.findViewById<TextView>(R.id.tv_index_name)!!
    }
}