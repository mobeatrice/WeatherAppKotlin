package com.example.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.ui.weather.WeatherFragment

class SettingsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_page)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, WeatherFragment())
            .commit()
    }

}