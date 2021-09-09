package com.example.avitoweather.ui.fragments.main_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.avitoweather.ui.fragments.weather_for_day.WeatherForDayFragment
import com.example.avitoweather.ui.fragments.weather_for_week.WeatherForWeekFragment

class WeatherPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> WeatherForDayFragment()
            else -> WeatherForWeekFragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        when (position) {
            0 -> "На день"
            else -> "На неделю"
        }
}