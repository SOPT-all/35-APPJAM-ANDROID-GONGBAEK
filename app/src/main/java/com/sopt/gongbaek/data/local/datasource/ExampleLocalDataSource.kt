package com.sopt.gongbaek.data.local.datasource

interface ExampleLocalDataSource {
    var localData: String
    fun getData(): String
}