package ru.alexander.worldmetrics.fragment.press_freedom

import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.InjectableFragment
import ru.alexander.worldmetrics.model.PressFreedomValue

private typealias Index = PressFreedomValue
private typealias ValueFunction = (Index) -> Float

class PressFreedomCountryDetailFragment :
    InjectableFragment(R.layout.country_detail_indexes) {
}