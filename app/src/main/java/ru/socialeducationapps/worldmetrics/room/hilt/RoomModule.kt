package ru.socialeducationapps.worldmetrics.room.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.socialeducationapps.worldmetrics.room.database.WorldMetricsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        WorldMetricsDatabase::class.java,
        "WorldMetricsDatabase"
    ).build()

    @Provides
    fun pressFreedomDao(db: WorldMetricsDatabase) = db.pressFreedomDao()
}