package com.example.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R


class WeatherFragment : Fragment(){

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.weatherData.observe(viewLifecycleOwner, Observer{ weatherData -> updateUI(weatherData)
        })

        viewModel.getWeatherData("cluj,ro", "9776a46de1466f977b46b9e5973dcd3d")

        val popupButton: Button = view.findViewById(R.id.settings_button)
        popupButton.setOnClickListener {
            showPopup(view)
        }

        return view
    }


    private fun updateUI(weatherData: WeatherData){
        view?.findViewById<TextView>(R.id.address)?.text = weatherData.address
        view?.findViewById<TextView>(R.id.updated_at)?.text = weatherData.updatedAtText
        view?.findViewById<TextView>(R.id.status)?.text = weatherData.weatherDescription
        view?.findViewById<TextView>(R.id.temp)?.text = weatherData.temp
        view?.findViewById<TextView>(R.id.temp_max)?.text = weatherData.tempMax
        view?.findViewById<TextView>(R.id.temp_min)?.text = weatherData.tempMin
        view?.findViewById<RelativeLayout>(R.id.mainContainer)?.visibility = View.VISIBLE
    }


    private fun showPopup(view: View)
    {
        val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.settings_page, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val popupTextView: TextView = popupView.findViewById((R.id.settingsText))
        popupTextView.text="Settings information about the app. More infromation in the future. "

        val closeButton: Button = popupView.findViewById(R.id.back_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(view, android.view.Gravity.CENTER, 0, 0)
    }
}