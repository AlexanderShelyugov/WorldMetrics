package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.LOADING
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.NOT_INITIALIZED

interface OverviewViewModel {
    fun getLastYearData(): Flow<ViewState>
    fun getColorCalculator(): ColorOfDataCalculator

    companion object {
        enum class DataStatus {
            NOT_INITIALIZED,
            LOADING,
            LOADING_SUCCESS,
            LOADING_ERROR,
        }

        data class ViewState(
            val status: DataStatus,
            val data: List<CountryFeatureValue>,
            val error: String = ""
        )

        fun initialState() = ViewState(NOT_INITIALIZED, emptyList())
        fun loadingState() = ViewState(LOADING, emptyList())
    }
}