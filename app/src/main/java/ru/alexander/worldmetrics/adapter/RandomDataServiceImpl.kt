package ru.alexander.worldmetrics.adapter

import javax.inject.Inject

class RandomDataServiceImpl @Inject constructor() : RandomDataService {
    override fun provideSomeData(): String {
        return "This is some data"
    }
}