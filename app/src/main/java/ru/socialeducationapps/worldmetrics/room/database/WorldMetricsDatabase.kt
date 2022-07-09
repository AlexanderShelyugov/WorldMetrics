package ru.socialeducationapps.worldmetrics.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.dao.PressFreedomDao
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.entity.PressFreedomIndexValue

@Database(
    entities = [PressFreedomIndexValue::class],
    version = 1,
)
abstract class WorldMetricsDatabase : RoomDatabase() {
    abstract fun pressFreedomDao(): PressFreedomDao
}