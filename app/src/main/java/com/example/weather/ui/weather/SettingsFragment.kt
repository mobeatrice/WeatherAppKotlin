package com.example.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.weather.R

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.settings_page, container, false)
        val backButton = view.findViewById<Button>(R.id.back_button)

        backButton.setOnClickListener {
            replaceFragment(WeatherFragment())
        }
        return view
    }

    private fun replaceFragment (fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.mainContainer, WeatherFragment())
        }
    }
}