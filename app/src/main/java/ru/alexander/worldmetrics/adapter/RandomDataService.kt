package ru.alexander.worldmetrics.adapter

import javax.inject.Inject

class RandomDataService @Inject constructor() {
    fun provideSomeData(): String {
        return "This is some data"
    }
}