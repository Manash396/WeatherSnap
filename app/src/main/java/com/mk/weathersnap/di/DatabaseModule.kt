package com.mk.weathersnap.di

import android.content.Context
import androidx.room.Room
import com.mk.weathersnap.data.local.dao.CityDao
import com.mk.weathersnap.data.local.database.WeatherSnapDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : WeatherSnapDb {
        return Room.databaseBuilder(
            context ,
            WeatherSnapDb::class.java,
            "weather_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCityDao(db : WeatherSnapDb) : CityDao = db.cityDao()

}