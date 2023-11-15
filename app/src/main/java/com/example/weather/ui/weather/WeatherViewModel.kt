package com.example.weather.ui.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherViewModel (application: Application): AndroidViewModel(application){

    private val weatherRepository = WeatherAPI()
    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> get() = _weatherData

    fun getWeatherData(city: String, apiKey: String){
        CoroutineScope(Dispatchers.IO).launch {
            var response: String?
            try {
                val response=weatherRepository.getWeather(city, apiKey)
                val weatherData= parseWeatherDate(response)
                _weatherData.postValue(weatherData)

            } catch (e: Exception)
            {
                response = null
            }
        }
    }

    private fun parseWeatherDate(response: String?): WeatherData {
        val jsonObj = JSONObject(response ?: "")

        val main = jsonObj.getJSONObject("main")
        val sys = jsonObj.getJSONObject("sys")
        val updatedAt: Long = jsonObj.getLong("dt")
        val updatedAtText =
            "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                Date(updatedAt * 1000)
            )
        val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
        val weatherDescription = weather.getString("main")
        val temp = main.getString("temp") + "°C"
        val tempMin="Min Temp: " + main.getString("temp_min")+ "°C"
        val tempMax="Max Temp: " + main.getString("temp_max")+ "°C"
        val address = jsonObj.getString("name") + ", " + sys.getString("country")

        return WeatherData(
            address = address,
            updatedAtText = updatedAtText,
            weatherDescription = weatherDescription,
            temp = temp,
            tempMin = tempMin,
            tempMax= tempMax
        )
    }


}