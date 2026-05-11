package com.mk.weathersnap.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.mk.weathersnap.data.local.OfflineDataSource
import com.mk.weathersnap.data.local.dao.CityDao
import com.mk.weathersnap.data.local.entity.toDomain
import com.mk.weathersnap.data.local.entity.toEntity
import com.mk.weathersnap.data.remote.RemoteDataSource
import com.mk.weathersnap.data.remote.api.ApiServices
import com.mk.weathersnap.data.remote.dto.toDomain
import com.mk.weathersnap.domain.model.City
import com.mk.weathersnap.domain.repository.WeatherRepository
import com.mk.weathersnap.util.ErrorType
import com.mk.weathersnap.util.UiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: OfflineDataSource
) : WeatherRepository{

    private var currentQuery = ""

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCitySuggestion(query: String): Flow<UiResult<List<City>>> {

        val localFlow = local.searchCities(query)
            .map { entities -> entities.map { it.toDomain() } }

        return localFlow
            .flatMapLatest { localCities ->

                if (localCities.isEmpty() && currentQuery != query) {
                    flow {
                        emit(UiResult.Loading)

                        try {
                            val response = remote.getCitySuggestion(query)

                            val remoteCities = response.results
                                ?.map { it.toDomain() }
                                ?: emptyList()

                            // Save to DB (no duplicates handled via Room)
                            local.insertCities(remoteCities)

                            // No need to emit Success here
                            // Room will emit automatically

                        } catch (e: Exception) {
                            emit(UiResult.Error(
                                e.message ?: "Network error",
                                type = ErrorType.UNKNOWN
                            ))
                        }
                    }
                } else {
                    flow {
                        emit(UiResult.Success(localCities)) // might empty list come from db
                    }
                }
            }
            .flowOn(Dispatchers.IO)
    }

}