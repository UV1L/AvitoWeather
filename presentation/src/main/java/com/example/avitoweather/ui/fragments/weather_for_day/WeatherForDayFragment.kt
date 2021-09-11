package com.example.avitoweather.ui.fragments.weather_for_day

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.avitoweather.R
import com.example.avitoweather.di.MyApplication
import com.example.avitoweather.vm.WeatherViewModel
import com.example.avitoweather.vm.WeatherViewModelFactory
import com.example.domain.entities.Weather

class WeatherForDayFragment(private val position: Int = -1) : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    private val weatherContainer: ConstraintLayout?
        get() = view?.findViewById(R.id.fragment_day_weather_container)

    private val weatherForDayTxt: TextView?
        get() = view?.findViewById(R.id.fragment_day_weather_txt)

    private val tempTxtView: TextView?
        get() = view?.findViewById(R.id.fragment_day_temp_txt_view)

    private val tempLabel: CardView?
        get() = view?.findViewById(R.id.fragment_day_temp_label)

    private val weatherDescription: TextView?
        get() = view?.findViewById(R.id.fragment_day_weather_description)

    private val errorContainer: ConstraintLayout?
        get() = view?.findViewById(R.id.fragment_day_error_container)

    private val errorMsg: TextView?
        get() = view?.findViewById(R.id.fragment_day_error_msg)


    private val pbContainer: ConstraintLayout?
        get() = view?.findViewById(R.id.fragment_day_pb_container)

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

        removeUnnecessaryView()
        observeAll()
    }

    private fun removeUnnecessaryView() {

        if (position != -1)
            weatherForDayTxt?.visibility = View.GONE
    }

    private fun observeAll() {

        viewModel.weather.observe(viewLifecycleOwner) { weather ->

            if (weather != null)
                setWeatherText(weather)
        }

        viewModel.exceptions.observe(viewLifecycleOwner) { errorMessage ->

            if (errorMessage != "")
                setVisibilitiesWhenException(errorMessage)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { flag ->

            if (flag)
                setVisibilitiesWhenLoading()
        }
    }

    private fun setWeatherText(weather: Weather) {

        setVisibilitiesWhenSuccess()

        if (position == -1) {
            weatherForDayTxt?.visibility = View.VISIBLE
            tempTxtView?.text = weather.tempC.toInt().toString()
            weatherDescription?.text = weather.description
        } else
        //мне пришлось сделать здесь try catch потому что я в самый последний момент понял, что
        // выбранное мною API, кажется, сломано, и выдает погоду только на 3 дня вместо 7
            try {
                weatherForDayTxt?.visibility = View.GONE
                val forecast = weather.forecast[position]
                tempTxtView?.text = forecast.tempC.toInt().toString()
                weatherDescription?.text = forecast.description
            } catch (e: IndexOutOfBoundsException) {
                setVisibilitiesWhenException("На выбранный день нет погоды")
                Log.d("no date", "Не удается загрузить погоду")
            }
    }

    private fun setVisibilitiesWhenSuccess() {

        viewModel.unsetLoading()
        pbContainer?.visibility = View.GONE
        errorContainer?.visibility = View.GONE
        weatherContainer?.visibility = View.VISIBLE
        tempTxtView?.visibility = View.VISIBLE
        tempLabel?.visibility = View.VISIBLE
    }

    private fun setVisibilitiesWhenException(errorMessage: String) {

        weatherContainer?.visibility = View.GONE
        pbContainer?.visibility = View.GONE
        errorContainer?.visibility = View.VISIBLE
        errorMsg?.text = errorMessage
    }

    private fun setVisibilitiesWhenLoading() {

        weatherContainer?.visibility = View.GONE
        errorContainer?.visibility = View.GONE
        pbContainer?.visibility = View.VISIBLE
    }
}