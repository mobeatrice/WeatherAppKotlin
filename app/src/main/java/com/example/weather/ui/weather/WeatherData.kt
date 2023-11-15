package com.example.weather.ui.weather

data class WeatherData(
    val address:String,
    val updatedAtText: String,
    val weatherDescription: String,
    val temp: String,
    val tempMin: String,
    val tempMax: String
)
