package com.app.openweather.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun WeatherScreen(
    city: String?,
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()

    LaunchedEffect(city) {
        city?.let { viewModel.getWeather(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = weatherState) {
            is WeatherState.Loading -> CircularProgressIndicator()
            is WeatherState.Success -> {
                Text(text = "Weather in $city")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Description: ${state.description}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Temperature: ${state.temperature}°C")
            }
            is WeatherState.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}