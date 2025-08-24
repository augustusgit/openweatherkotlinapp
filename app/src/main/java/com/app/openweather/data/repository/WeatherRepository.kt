package com.app.openweather.data.repository

import com.app.openweather.data.model.WeatherResponse
import com.app.openweather.data.remote.OpenWeatherApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: OpenWeatherApiService
) {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return apiService.getWeather(city, apiKey)
    }
}