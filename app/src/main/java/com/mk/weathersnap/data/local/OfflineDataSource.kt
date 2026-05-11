package com.mk.weathersnap.data.local

import com.mk.weathersnap.data.local.dao.CityDao
import com.mk.weathersnap.data.local.entity.CityEntity
import com.mk.weathersnap.data.local.entity.toDomain
import com.mk.weathersnap.data.local.entity.toEntity
import com.mk.weathersnap.domain.model.City
import com.mk.weathersnap.util.UiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineDataSource @Inject constructor(
    private val cityDao: CityDao
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun searchCities(query: String): Flow<List<CityEntity>> {
        return cityDao.observeCities(query)
    }

    suspend fun insertCities(list : List<City>){
        cityDao.insertCities(list.map { it.toEntity() })
    }

}