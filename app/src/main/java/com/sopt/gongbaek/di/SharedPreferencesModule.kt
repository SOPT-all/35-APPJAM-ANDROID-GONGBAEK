package com.sopt.gongbaek.di

import android.content.Context
import android.content.SharedPreferences
import com.sopt.gongbaek.di.qualifier.LectureTimetablePrefs
import com.sopt.gongbaek.di.qualifier.TokenPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @TokenPrefs
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("token_prefs", Context.MODE_PRIVATE)

    @LectureTimetablePrefs
    @Provides
    @Singleton
    fun provideLectureTimetableSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("lecture_timetable_prefs", Context.MODE_PRIVATE)
}
