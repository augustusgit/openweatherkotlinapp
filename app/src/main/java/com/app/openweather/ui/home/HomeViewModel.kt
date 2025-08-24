package com.app.openweather.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _favoriteCity = MutableStateFlow("")
    val favoriteCity = _favoriteCity.asStateFlow()

    init {
        loadFavoriteCity()
    }

    private fun loadFavoriteCity() {
        _favoriteCity.value = sharedPreferences.getString("favorite_city", "Lagos") ?: ""
    }

    fun saveFavoriteCity(city: String) {
        viewModelScope.launch {
            sharedPreferences.edit().putString("favorite_city", city).apply()
            _favoriteCity.value = city
        }
    }
}