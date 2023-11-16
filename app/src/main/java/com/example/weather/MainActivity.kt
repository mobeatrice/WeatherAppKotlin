package com.example.weather

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.ui.weather.WeatherFragment

class MainActivity : AppCompatActivity()
{



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var settingsButton = findViewById<Button>(R.id.settings_button)

        //scroll menu
        var textview: TextView = findViewById(R.id.detailsText)
        val para = """A lot of things happened today. 
            |A lot of things happened today. 
            |A lot of things happened today.
            |A lot of things happened today.
            |A lot of things happened today.
            |A lot of things happened today.
            |A lot of things happened today.
            |A lot of things happened today.
        """.trimMargin()
        textview.text=para
        textview.movementMethod = ScrollingMovementMethod()

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, WeatherFragment())
            .commit()
    }
}

