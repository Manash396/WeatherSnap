package com.mk.weathersnap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mk.weathersnap.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities where name LIKE '%' || :query || '%'")
    fun observeCities(query: String) : Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cities: List<CityEntity>)
}