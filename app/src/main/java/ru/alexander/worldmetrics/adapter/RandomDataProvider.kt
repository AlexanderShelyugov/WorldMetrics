package ru.alexander.worldmetrics.adapter

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomDataProvider @Inject constructor(
    private val service: RandomDataService
) {
    fun provideSomeData(): String {
        return service.provideSomeData() + "!"
    }
}