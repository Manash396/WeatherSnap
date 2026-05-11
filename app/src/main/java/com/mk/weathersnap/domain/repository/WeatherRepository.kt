package com.mk.weathersnap.domain.repository

import com.mk.weathersnap.domain.model.City
import com.mk.weathersnap.util.UiResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCitySuggestion(query : String) : Flow<UiResult<List<City>>>
}