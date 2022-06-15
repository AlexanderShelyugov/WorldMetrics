package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter.LVCViewHolder
import ru.alexander.worldmetrics.global.ColorAccess.Companion.getColor
import ru.alexander.worldmetrics.view.LabelValueChartView

private typealias VH<T> = LVCViewHolder<T>

abstract class IndexFeaturesRVAdapter<T> : Adapter<VH<T>>() {

    private var items: List<T> = emptyList()

    fun setData(data: List<T>) {
        items = data
    }

    class LVCViewHolder<T>(
        val lcv: LabelValueChartView<T>
    ) : RecyclerView.ViewHolder(lcv)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): VH<T> {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.label_value_chart_view, viewGroup, false
        ) as LabelValueChartView<T>
        view.setData(items)
        view.setBackgroundColor(getColor(R.color.orange))
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH<T>, position: Int) {
        holder.lcv.run {
            setLabelText(getFeatureName(position))
            getFeatureExtractors(position).run {
                setExtractors(first, second)
            }
            refresh()
        }
    }

    override fun getItemCount(): Int = getFeaturesNumber()

    protected abstract fun getFeatureName(index: Int): Int

    protected abstract fun getFeatureExtractors(index: Int): Pair<(T) -> Float, (T) -> Float>

    protected abstract fun getFeaturesNumber(): Int

}