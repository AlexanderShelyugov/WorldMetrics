package ru.alexander.worldmetrics.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.ContextAccess.Companion.context
import ru.alexander.worldmetrics.model.CountriesData
import javax.inject.Inject

@HiltViewModel
class CurrentCountryViewModel @Inject constructor() : ViewModel() {
    private val countryCodeContainer = MutableLiveData<String>()

    val currentCountryCode: LiveData<String> by lazy {
        countryCodeContainer
    }

    init {
        loadFromPreferences()
    }

    fun setCurrentCountryCode(countryCode: String) {
        if (CountriesData.CODES_TO_NAMES.keys.contains(countryCode)) {
            countryCode
        } else {
            ""
        }.let { country ->
            saveToPreferences(country)
            countryCodeContainer.value = country
        }

    }

    fun loadFromPreferences() {
        val preferences = context.getSharedPreferences(
            context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE
        )
        val countryCode =
            preferences.getString(context.getString(R.string.preference_current_country), "")!!
        setCurrentCountryCode(countryCode)
    }

    private fun saveToPreferences(countryCode: String) {
        val preferences = context.getSharedPreferences(
            context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE
        )
        preferences.edit()
            .putString(context.getString(R.string.preference_current_country), countryCode)
            .apply()
    }
}