package ru.socialeducationapps.worldmetrics.feature.country.current.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.utils.ContextAccess.Companion.context
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getAllCountryCodes
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
        val code = countryCode.lowercase()
            .takeIf { getAllCountryCodes().contains(it) }
            ?: ""
        saveToPreferences(code)
        countryCodeContainer.value = code
    }

    private fun loadFromPreferences() {
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