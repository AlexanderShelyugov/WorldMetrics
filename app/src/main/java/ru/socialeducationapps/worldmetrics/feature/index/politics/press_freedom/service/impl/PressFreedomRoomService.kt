package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.impl

import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.room.dao.PressFreedomDao
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.room.entity.PressFreedomIndexValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.toFeatureMedianRange
import javax.inject.Inject

class PressFreedomRoomService @Inject constructor(
    private val dao: PressFreedomDao,
) : PressFreedomService {
    private companion object {
        val RANGE_VALUES = 13.92f to 92.65f
        val RANGE_POLITICAL_CONTEXT = 22.22f to 94.89f
        val RANGE_ECONOMIC_CONTEXT = 0.0f to 90.38f
        val RANGE_LEGAL_CONTEXT = 15.79f to 92.23f
        val RANGE_SOCIAL_CONTEXT = 12.0f to 95.0f
        val RANGE_SAFETY = 4.63f to 96.46f
    }

    override suspend fun getLastYearData() = dao.getLastYearData()

    override suspend fun getLastYearData(countryCode: String) = dao.getLastYearData(countryCode)
        .let(this::rowToIndexValue)

    override suspend fun getAllData(countryCode: String) = dao.getAllData(countryCode)
        .map(this::rowToIndexValue)

    override fun getValueRange() = RANGE_VALUES.toFeatureMedianRange()
    override suspend fun getMinMedianMaxForAllCountries() = getValueRange()

    override fun getPCRange() = RANGE_POLITICAL_CONTEXT.toFeatureMedianRange()
    override fun getECRange() = RANGE_ECONOMIC_CONTEXT.toFeatureMedianRange()
    override fun getLCRange() = RANGE_LEGAL_CONTEXT.toFeatureMedianRange()
    override fun getSCRange() = RANGE_SOCIAL_CONTEXT.toFeatureMedianRange()
    override fun getSRange() = RANGE_SAFETY.toFeatureMedianRange()

    private fun rowToIndexValue(row: PressFreedomIndexValue) = PressFreedomValue(
        row.iso3CountryCode,
        row.pressFreedom,
        row.politicalContext,
        row.economicContext,
        row.legalContext,
        row.socialContext,
        row.safety,
        row.year,
    )
}