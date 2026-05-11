package com.mk.weathersnap.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mk.weathersnap.data.local.dao.CityDao
import com.mk.weathersnap.data.local.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherSnapDb : RoomDatabase() {

    abstract fun cityDao(): CityDao
}