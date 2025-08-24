package com.app.openweather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.openweather.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                // Replace with your actual API key
                val response = repository.getWeather(city, "ff2ed7336fe84267efd11579a2083b8f")
                _weatherState.value = WeatherState.Success(
                    description = response.weather.firstOrNull()?.description ?: "N/A",
                    temperature = response.main.temp
                )
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val description: String, val temperature: Double) : WeatherState()
    data class Error(val message: String) : WeatherState()
}