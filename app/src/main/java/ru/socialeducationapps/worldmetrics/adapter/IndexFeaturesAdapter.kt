package ru.socialeducationapps.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter.LVCViewHolder
import ru.socialeducationapps.worldmetrics.global.ColorAccess
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureExtractor
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.view.LabelValueChartView

private typealias VH<T> = LVCViewHolder<T>

class IndexFeaturesRVAdapter<T>(
    private val featureName: (Int) -> Int,
    private val featureExtractors: (Int) -> Pair<FeatureExtractor<T>, FeatureExtractor<T>>,
    private val featuresNumber: Int,
) : Adapter<VH<T>>() {

    private var items: List<T> = emptyList()
    private var ranges: List<FeatureRange> = emptyList()

    fun setData(data: List<T>) {
        items = data
        notifyItemRangeChanged(0, itemCount)
    }

    fun setFeatureRanges(ranges: List<FeatureRange>) {
        this.ranges = ranges
        notifyItemRangeChanged(0, itemCount)
    }

    class LVCViewHolder<T>(
        val lcv: LabelValueChartView<T>
    ) : RecyclerView.ViewHolder(lcv)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): VH<T> = (LayoutInflater.from(viewGroup.context).inflate(
        R.layout.label_value_chart_view, viewGroup, false
    ) as LabelValueChartView<T>)
        .run {
            setData(items)
            VH(this)
        }

    override fun onBindViewHolder(holder: VH<T>, position: Int) {
        holder.lcv.run {
            setLabelText(featureName(position))
            if (ranges.isNotEmpty()) {
                setRangeColors(ColorAccess.DEFAULT_COLOR_CALCULATOR, ranges[position])
            }
            featureExtractors(position).run {
                setExtractors(first, second)
            }
            refresh()
        }
    }

    override fun getItemCount() = featuresNumber
}