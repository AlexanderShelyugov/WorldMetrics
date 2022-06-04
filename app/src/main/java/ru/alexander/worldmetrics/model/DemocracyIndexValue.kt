package ru.alexander.worldmetrics.model

import kotlin.Float.Companion.NaN
import kotlin.reflect.full.memberProperties

data class DemocracyIndexValue(
    val countryCode: String,
    val country: String,
    val year: Int,
    val democracyIndex: Float,
    val electoralProcessAndPluralism: Float,
    val functioningOfGovernment: Float,
    val politicalParticipation: Float,
    val politicalCulture: Float,
    val civilLiberties: Float,
) {
    companion object {
        fun emptyValue(): DemocracyIndexValue = DemocracyIndexValue(
            "", "", 0, NaN, NaN, NaN, NaN, NaN, NaN
        )
    }
}

fun DemocracyIndexValue.asMap(): Map<String, String> {
    val props = DemocracyIndexValue::class.memberProperties.associateBy { it.name }
    return props.keys.associateWith { props[it]?.get(this).toString() }
}