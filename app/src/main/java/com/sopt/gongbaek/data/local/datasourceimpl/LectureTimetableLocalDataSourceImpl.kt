package com.sopt.gongbaek.data.local.datasourceimpl

import android.content.SharedPreferences
import com.sopt.gongbaek.data.local.datasource.LectureTimetableLocalDataSource
import com.sopt.gongbaek.di.qualifier.LectureTimetablePrefs
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LectureTimetableLocalDataSourceImpl @Inject constructor(
    @LectureTimetablePrefs private val sharedPreferences: SharedPreferences
) : LectureTimetableLocalDataSource {

    override fun setTimetable(lectureTimetable: Map<String, List<Int>>) {
        val jsonString = Json.encodeToString(lectureTimetable)
        sharedPreferences.edit().putString(KEY, jsonString).apply()
    }

    override fun getTimetable(): Map<String, List<Int>> {
        val jsonString = sharedPreferences.getString(KEY, null) ?: return emptyMap()
        return try {
            Json.decodeFromString(jsonString)
        } catch (e: SerializationException) {
            emptyMap()
        }
    }

    companion object {
        private const val KEY = "lecture_timetable"
    }
}
