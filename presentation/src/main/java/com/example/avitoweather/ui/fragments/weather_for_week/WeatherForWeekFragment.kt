package com.example.avitoweather.ui.fragments.weather_for_week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.avitoweather.R
import com.google.android.material.tabs.TabLayout

class WeatherForWeekFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_weather_for_week, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager =
            requireView().findViewById<ViewPager>(R.id.fragment_weather_for_week_view_pager)
        viewPager.adapter = WeekPagerAdapter(parentFragmentManager)
        val tabLayout =
            requireView().findViewById<TabLayout>(R.id.fragment_weather_for_week_tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}