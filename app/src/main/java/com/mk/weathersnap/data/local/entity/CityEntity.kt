package com.mk.weathersnap.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mk.weathersnap.domain.model.City

@Entity(
    tableName = "cities",
    indices = [Index(value = ["name", "country", "latitude", "longitude"], unique = true)]
)
data class CityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val state: String?
)

fun City.toEntity(): CityEntity {
    return CityEntity(
        id = id,
        name = name,
        latitude = latitude,
        longitude = longitude,
        country = country,
        state = state
    )
}

fun CityEntity.toDomain(): City {
    return City(
        id = id,
        name = name,
        latitude = latitude,
        longitude = longitude,
        country = country,
        state = state
    )
}
