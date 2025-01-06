package com.sopt.gongbaek.data.local.datasourceimpl

import com.sopt.gongbaek.data.local.datasource.ExampleLocalDataSource
import javax.inject.Inject

class ExampleLocalDataSourceImpl @Inject constructor() : ExampleLocalDataSource {
    override var localData: String
        get() = getData()
        set(value) { }

    override fun getData(): String {
        return "Local Data"
    }
}
