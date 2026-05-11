package com.mk.weathersnap.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.mk.weathersnap.data.remote.api.ApiServices
import com.mk.weathersnap.data.remote.dto.toDomain
import com.mk.weathersnap.domain.model.City
import com.mk.weathersnap.util.ErrorType
import com.mk.weathersnap.util.UiResult
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
){

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getCitySuggestion(query : String) : UiResult<List<City>> {

        return try {
            val response = apiServices.searchCities(query)
            val cities = response.results
                ?.map { it.toDomain() }
                ?: emptyList()

            if (cities.isEmpty()) {
                UiResult.Empty
            } else {
                UiResult.Success(cities)
            }

        } catch (e: HttpException) {
            UiResult.Error(
                e.message ?: "Error",
                ErrorType.SERVER
            )
        } catch (e: IOException) {
            // No internet / timeout
            UiResult.Error(
                message = "Check your internet connection",
                type = ErrorType.NETWORK
            )
        }
    }

}