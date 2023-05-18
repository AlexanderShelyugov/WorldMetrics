package ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.view.LabelValueChartView
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CommonIndexLayout
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter.IndexFeaturesRVAdapter.LVCViewHolder
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorGradeCalculator

private typealias VH<T> = LVCViewHolder<T>

class IndexFeaturesRVAdapter<T>(
    private val layout: CommonIndexLayout<T>,
) : Adapter<VH<T>>() {

    private var items: List<T> = emptyList()
    private var ranges: List<FeatureRange> = emptyList()
    private lateinit var colorCalculator: ColorGradeCalculator

    fun setData(data: List<T>) {
        items = data
        notifyItemRangeChanged(0, itemCount)
    }

    fun setColorCalculator(colorGradeCalculator: ColorGradeCalculator) {
        this.colorCalculator = colorGradeCalculator
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
            setLabelText(layout.featureName(position))
            if (ranges.isNotEmpty()) {
                setRangeColors(colorCalculator, ranges[position])
            }
            setExtractors(layout.yearFunction, layout.features[position].second)
            refresh()
        }
    }

    override fun getItemCount() = layout.featuresNumber
}