package com.mk.weathersnap.data.remote.dto

import com.mk.weathersnap.domain.model.City

data class CityDto(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double?,
    val feature_code: String?,
    val country_code: String,
    val country: String,
    val admin1: String?,
    val admin2: String?,
    val admin3: String?,
    val admin4: String?,
    val timezone: String,
    val population: Int?,
    val postcodes: List<String>?
)

fun CityDto.toDomain(): City {
    return City(
        id = id,
        name = name,
        latitude = latitude,
        longitude = longitude,
        country = country,
        state = admin1
    )
}
