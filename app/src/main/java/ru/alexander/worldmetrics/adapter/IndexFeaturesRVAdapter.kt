package ru.alexander.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter.LVCViewHolder
import ru.alexander.worldmetrics.view.LabelValueChartView

abstract class IndexFeaturesRVAdapter<T> : Adapter<LVCViewHolder<T>>() {

    protected lateinit var items: List<T>

    fun setData(data: List<T>) {
        items = data
    }

    class LVCViewHolder<T>(
        val lcv: LabelValueChartView<T>
    ) : RecyclerView.ViewHolder(lcv)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): LVCViewHolder<T> {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.label_value_chart_view,
                viewGroup,
                false
            ) as LabelValueChartView<T>
        view.setData(items)
        return LVCViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: LVCViewHolder<T>,
        position: Int
    ) {
        holder.lcv.run {
            setLabelText(getFeatureName(position))
            val extractors = getFeatureExtractors(position)
            setExtractors(extractors.first, extractors.second)
        }
    }

    override fun getItemCount(): Int = getFeaturesNumber()

    protected abstract fun getFeatureName(index: Int): Int

    protected abstract fun getFeatureExtractors(index: Int): Pair<(T) -> Float, (T) -> Float>

    protected abstract fun getFeaturesNumber(): Int

}