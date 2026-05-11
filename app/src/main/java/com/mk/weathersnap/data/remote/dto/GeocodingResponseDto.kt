package com.mk.weathersnap.data.remote.dto

data class GeocodingResponseDto(
    val results: List<CityDto>?,
    val generationtime_ms: Double
)