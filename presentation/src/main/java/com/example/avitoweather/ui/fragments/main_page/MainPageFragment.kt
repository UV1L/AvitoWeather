package com.example.avitoweather.ui.fragments.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.avitoweather.R
import com.example.avitoweather.di.MyApplication
import com.example.avitoweather.vm.WeatherViewModel
import com.example.avitoweather.vm.WeatherViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

        setEditTxtListener()
        setupTabLayout()
    }

    private fun setEditTxtListener() {
        val editTxt = requireView().findViewById<TextInputEditText>(R.id.fragment_main_edit_txt)

        editTxt.doOnTextChanged { text, _, _, _ ->

            viewModel.loadWeather(text.toString())
        }

        requireView().setOnClickListener {
            editTxt.clearFocus()
        }
    }

    private fun setupTabLayout() {
        val viewPager = requireView().findViewById<ViewPager>(R.id.fragment_main_view_pager)
        viewPager.adapter = WeatherPagerAdapter(parentFragmentManager)
        val tabLayout = requireView().findViewById<TabLayout>(R.id.fragment_main_tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}