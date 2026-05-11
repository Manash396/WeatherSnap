package com.mk.weathersnap.di

import com.mk.weathersnap.data.repository.WeatherRepositoryImpl
import com.mk.weathersnap.domain.repository.WeatherRepository
import dagger.Binds

abstract class WeatherRepoModule {

    @Binds
    abstract fun bindWeatherRepository(
        impl : WeatherRepositoryImpl
    ) : WeatherRepository
}