package ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.view.LabelValueChartView
import ru.socialeducationapps.worldmetrics.feature.helper.view.LabelValueChartView.Companion.LVCVState
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureExtractor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout
import ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter.IndexFeaturesRVAdapter.LVCViewHolder
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator

private typealias VH = LVCViewHolder

/**
 * Displays all features of a given index
 */
class IndexFeaturesRVAdapter<IndexType>(
    private val layout: IndexFeaturesLayout<IndexType>,
    private val colorCalculators: Map<Int, ColorOfDataCalculator>
) : Adapter<VH>() {

    private var adapterState = AdapterState<IndexType>(emptyList())

    fun setState(adapterState: AdapterState<IndexType>) {
        this.adapterState = adapterState
        notifyItemRangeChanged(0, itemCount)
    }

    class LVCViewHolder(
        val lcv: LabelValueChartView
    ) : RecyclerView.ViewHolder(lcv)

    override fun getItemCount() = adapterState.features.size

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): VH = (LayoutInflater.from(viewGroup.context).inflate(
        R.layout.label_value_chart_view, viewGroup, false
    ) as LabelValueChartView)
        .run { VH(this) }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val ctx = holder.lcv.context
        val featureId = layout.features[position].first
        val featureName = ctx.getString(featureId)
        val chartData = adapterState.features[position].let { adapterItem ->
            adapterItem.data
                .mapIndexed { index, dataItem ->
                    index.toFloat() to adapterItem.valueForYearExtractor(
                        dataItem
                    )
                }
        }.toList()
        holder.lcv.apply {
            setState(LVCVState(featureName, chartData))
            setColorCalculator(
                colorCalculators[featureId]
            )
            refresh()
        }
    }

    companion object {
        /**
         * Overall state of this Adapter
         */
        data class AdapterState<IndexType>(
            internal val features: List<IndexFeatureAdapterItem<IndexType>>
        )

        /**
         * Contains all required data for the single adapter item
         */
        data class IndexFeatureAdapterItem<IndexType>(
            internal val data: List<IndexType>,
            internal val valueForYearExtractor: FeatureExtractor<IndexType>,
        )
    }
}