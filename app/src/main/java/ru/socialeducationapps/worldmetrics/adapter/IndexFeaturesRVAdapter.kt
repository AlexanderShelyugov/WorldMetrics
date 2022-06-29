package ru.socialeducationapps.worldmetrics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter.LVCViewHolder
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureExtractor
import ru.socialeducationapps.worldmetrics.view.LabelValueChartView

private typealias VH<T> = LVCViewHolder<T>

class IndexFeaturesRVAdapter<T>(
    private val featureName: (Int) -> Int,
    private val featureExtractors: (Int) -> Pair<FeatureExtractor<T>, FeatureExtractor<T>>,
    private val featuresNumber: Int,
) : Adapter<VH<T>>() {

    private var items: List<T> = emptyList()
    private var valueColors: List<Int> = emptyList()

    fun setData(data: List<T>) {
        items = data
    }

    fun setValueColors(colors: List<Int>) {
        valueColors = colors
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
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH<T>, position: Int) {
        holder.lcv.run {
            setLabelText(featureName(position))
            if (valueColors.isNotEmpty()) {
                setValueColor(valueColors[position])
            }
            featureExtractors(position).run {
                setExtractors(first, second)
            }
            refresh()
        }
    }

    override fun getItemCount() = featuresNumber
}