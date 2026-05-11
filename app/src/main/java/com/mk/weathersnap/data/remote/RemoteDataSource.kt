package com.mk.weathersnap.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.mk.weathersnap.data.remote.api.ApiServices
import com.mk.weathersnap.data.remote.dto.GeocodingResponseDto
import com.mk.weathersnap.data.remote.dto.toDomain
import com.mk.weathersnap.domain.model.City
import com.mk.weathersnap.util.ErrorType
import com.mk.weathersnap.util.UiResult
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices,
){

    suspend fun getCitySuggestion(query : String) : GeocodingResponseDto {
       return apiServices.searchCities(query)
    }

}