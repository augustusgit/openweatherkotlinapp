package com.app.openweather.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val favoriteCity by viewModel.favoriteCity.collectAsState()
    var city by remember { mutableStateOf(favoriteCity) }

    LaunchedEffect(favoriteCity) {
        city = favoriteCity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (city.isNotBlank()) {
                navController.navigate("weather/$city")
            }
        }) {
            Text("Get Weather")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.saveFavoriteCity(city)
        }) {
            Text("Save as Favorite")
        }
    }
}