package com.example.avitoweather.ui.fragments.weather_for_week

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.avitoweather.ui.fragments.weather_for_day.WeatherForDayFragment
import java.text.SimpleDateFormat
import java.util.*

class WeekPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = 7

    override fun getItem(position: Int): Fragment =
        WeatherForDayFragment(position)

    override fun getPageTitle(position: Int): CharSequence {

        val weekDayName = WeekDayName.getWeekNameByPosition(position)

        return when (weekDayName) {
            "Monday" -> "Пн"
            "Tuesday" -> "Вт"
            "Wednesday" -> "Ср"
            "Thursday" -> "Чт"
            "Friday" -> "Пт"
            "Saturday" -> "Сб"
            else -> "Вс"
        }
    }
}

class WeekDayName {

    companion object {

        fun getWeekNameByPosition(position: Int): String {

            var sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = sdf.format(Date())
            val calendar = Calendar.getInstance()
            calendar.time = sdf.parse(date)!!
            calendar.add(Calendar.DATE, position)
            val resultDate = Date(calendar.timeInMillis)

            sdf = SimpleDateFormat("EEEE")
            return sdf.format(resultDate)
        }
    }
}