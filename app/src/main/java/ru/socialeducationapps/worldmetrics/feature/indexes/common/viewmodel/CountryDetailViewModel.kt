package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator

interface CountryDetailViewModel<T> {
    fun getViewState(): Flow<ViewState<T>>
    fun getColorCalculator(indexFeature: Int): ColorOfDataCalculator
    fun setCountry(country: String)
    fun onOpen()

    companion object {
        sealed class ViewState<T> {
            class Initial<T> : ViewState<T>()
            class Loading<T> : ViewState<T>()
            data class Success<T>(val lastYearData: T, val allData: List<T>) : ViewState<T>()
            data class Error<T>(val exception: Throwable) : ViewState<T>()
        }
    }
}