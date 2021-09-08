package com.example.avitoweather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.avitoweather.R
import com.example.avitoweather.di.MyApplication
import com.example.avitoweather.vm.WeatherViewModel
import com.example.avitoweather.vm.WeatherViewModelFactory

class MainPageFragment : Fragment()  {

    companion object {
        fun instance() = MainPageFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherUseCase = (requireActivity().application as MyApplication)
            .applicationComponent
            .inject()

        viewModel = ViewModelProvider(requireActivity(), WeatherViewModelFactory(weatherUseCase))
            .get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tempTxtView = requireView().findViewById<TextView>(R.id.fragment_main_temp_txt_view)

        viewModel.loadWeather("Moscow")
        viewModel.weather.observe(viewLifecycleOwner) {
            tempTxtView.text = it.tempC.toString()
        }
    }
}