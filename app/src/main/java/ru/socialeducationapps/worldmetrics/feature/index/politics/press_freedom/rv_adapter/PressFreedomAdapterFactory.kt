package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue

class PressFreedomAdapterFactory private constructor() {
    companion object {
        fun getPressFreedomFeaturesAdapter(): LabelValueChartRVAdapter<PressFreedomValue> =
            LabelValueChartRVAdapter()
    }
}