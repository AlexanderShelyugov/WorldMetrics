package ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.view.LabelValueChartView
import ru.socialeducationapps.worldmetrics.feature.helper.view.LabelValueChartView.Companion.LVCVState
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureExtractor
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter.LVCViewHolder
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator

private typealias VH = LVCViewHolder

/**
 * Displays all features of a given index
 */
class LabelValueChartRVAdapter<DataItemType> : Adapter<VH>() {

    private var adapterState = AdapterState<DataItemType>(emptyList())

    fun setState(adapterState: AdapterState<DataItemType>) {
        this.adapterState = adapterState
        notifyItemRangeChanged(0, itemCount)
    }

    class LVCViewHolder(
        val lcv: LabelValueChartView
    ) : RecyclerView.ViewHolder(lcv)

    override fun getItemCount() = adapterState.dataOfItems.size

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): VH = (LayoutInflater.from(viewGroup.context).inflate(
        R.layout.label_value_chart_view, viewGroup, false
    ) as LabelValueChartView)
        .let { VH(it) }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val ctx = holder.lcv.context
        val informationForThisItem = adapterState.dataOfItems[position]
        val labelText = ctx.getString(informationForThisItem.labelTextId)
        val chartData = informationForThisItem.data
            .mapIndexed { i, dataItem ->
                i.toFloat() to informationForThisItem.chartYValueExtractor(dataItem)
            }.toList()
        holder.lcv.apply {
            setState(LVCVState(labelText, chartData))
            setColorCalculator(informationForThisItem.colorCalculator)
            refresh()
        }
    }

    companion object {
        /**
         * Overall state of this Adapter
         */
        data class AdapterState<IndexType>(
            internal val dataOfItems: List<IndexFeatureAdapterItem<IndexType>>
        )

        /**
         * Contains all required data for the single adapter item
         */
        data class IndexFeatureAdapterItem<DataItemType>(
            internal val labelTextId: Int,
            internal val data: List<DataItemType>,
            internal val chartYValueExtractor: FeatureExtractor<DataItemType>,
            internal val colorCalculator: ColorOfDataCalculator?
        )
    }
}