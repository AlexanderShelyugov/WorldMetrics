package ru.socialeducationapps.worldmetrics.feature.countries_comparison.rv_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue

class CountriesComparisonRVAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var state: CountriesComparisonRVAdapterState = emptyStateInstance()

    fun setModel(model: CountriesComparisonRVAdapterState) {
        this.state = model
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return state.getTotalItemsCount()
    }

    override fun getItemViewType(position: Int): Int {
        return state.getItemViewType(position)
    }

    companion object {
        data class CountriesComparisonRVAdapterState(
            val indexComparisons: List<ComparisonOnIndex>,
        )

        fun emptyStateInstance(): CountriesComparisonRVAdapterState {
            return CountriesComparisonRVAdapterState(emptyList())
        }

        fun CountriesComparisonRVAdapterState.getTotalItemsCount(): Int {
            return indexComparisons.size +
                    indexComparisons.flatMap { it.featureComparisons }.count()
        }

        fun CountriesComparisonRVAdapterState.getItemViewType(position: Int): Int {
            return 0
        }

        data class ComparisonOnIndex(
            val indexId: Int,
            val featureComparisons: List<ComparisonOnFeature>,
        )

        data class ComparisonOnFeature(
            val featureId: Int,
            val valueA: FeatureValue,
            val valueB: FeatureValue,
        )
    }

}