package com.example.weather.ui.weather

import java.net.URL

class WeatherAPI {
    suspend fun getWeather(city:String, api: String): String {
        return URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$api")
            .readText(Charsets.UTF_8)
    }
}