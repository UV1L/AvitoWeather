package com.example.avitoweather.ui.fragments.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.core.view.isVisible
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

class MainPageFragment : Fragment() {

    companion object {

        fun instance() = MainPageFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    private val txtInputLayout: TextInputLayout?
        get() = view?.findViewById(R.id.fragment_main_text_input_layout)

    private val editTxt: TextInputEditText?
        get() = view?.findViewById(R.id.fragment_main_edit_txt)

    private val viewPager: ViewPager?
        get() = view?.findViewById(R.id.fragment_main_view_pager)

    private val tabLayout: TabLayout?
        get() = view?.findViewById(R.id.fragment_main_tab_layout)

    private val searchBtn: ImageButton?
        get() = view?.findViewById(R.id.fragment_main_search_btn)

    private val searchBtnAnimation: Animation?
        get() = lazy {
            AnimationUtils.loadAnimation(context, R.anim.search_button_animation)
        }.value

    private val editTextAnimation: Animation?
        get() = lazy {
            AnimationUtils.loadAnimation(context, R.anim.edit_text_animation)
        }.value

    private val translateFromDownAnimation: Animation?
        get() = lazy {
            AnimationUtils.loadAnimation(context, R.anim.translate_from_down_animation)
        }.value

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

        setSearchBtnListener()
        setEditTxtListener()
        setupTabLayout()
    }

    private fun setSearchBtnListener() {

        searchBtn?.setOnClickListener {

            if (editTxt?.text.toString() == "") {
                searchBtn?.startAnimation(searchBtnAnimation)
                txtInputLayout?.startAnimation(editTextAnimation)
            }
            else {
                if (!tabLayout?.isVisible!!) {
                    tabLayout?.visibility = View.VISIBLE
                    tabLayout?.startAnimation(translateFromDownAnimation)
                }
                viewModel.loadWeather(editTxt?.text.toString())
            }
        }
    }

    private fun setEditTxtListener() {

        requireView().setOnClickListener {

            editTxt?.clearFocus()
        }
    }

    private fun setupTabLayout() {

        viewPager?.adapter = WeatherPagerAdapter(parentFragmentManager)
        tabLayout?.setupWithViewPager(viewPager)
    }
}