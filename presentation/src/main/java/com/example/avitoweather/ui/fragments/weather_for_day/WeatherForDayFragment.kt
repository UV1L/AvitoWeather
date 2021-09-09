package com.example.avitoweather.ui.fragments.weather_for_day

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.avitoweather.R
import com.example.avitoweather.di.MyApplication
import com.example.avitoweather.ui.fragments.weather_for_week.WeekDayName
import com.example.avitoweather.vm.WeatherViewModel
import com.example.avitoweather.vm.WeatherViewModelFactory
import com.example.domain.entities.Weather
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class WeatherForDayFragment(private val position: Int = -1) : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_weather_for_day, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherUseCase = (requireActivity().application as MyApplication)
            .applicationComponent
            .inject()

        viewModel = ViewModelProvider(requireActivity(), WeatherViewModelFactory(weatherUseCase))
            .get(WeatherViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherForDayTxt = requireView().findViewById<TextView>(R.id.fragment_main_weather_txt)

        if (position != -1)
            weatherForDayTxt.visibility = TextView.GONE

        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            if (weather != null) {
                setWeatherText(weather)
            }
        }
    }

    private fun setWeatherText(weather: Weather) {

        val weatherForDayTxt = requireView().findViewById<TextView>(R.id.fragment_main_weather_txt)
        val tempTxtView = requireView().findViewById<TextView>(R.id.fragment_main_temp_txt_view)
        val tempLabel = requireView().findViewById<CardView>(R.id.fragment_main_temp_label)
        tempLabel.visibility = CardView.VISIBLE

        if (position == -1)
            tempTxtView.text = weather.tempC.toInt().toString()
        else
            //мне пришлось сделать здесь try catch потому что я в самый последний момент понял, что
                // выбранное мною API, кажется, сломано, и выдает погоду только на 3 дня вместо 7
            try {
                tempTxtView.text = weather.forecast[position].toInt().toString()
            } catch (e: IndexOutOfBoundsException) {
                weatherForDayTxt.visibility = TextView.VISIBLE
                weatherForDayTxt.text = "Не удалось загрузить погоду на выбранный день :("
                tempLabel.visibility = CardView.GONE
                Log.d("no date", "Не удается загрузить погоду")
            }
    }
}