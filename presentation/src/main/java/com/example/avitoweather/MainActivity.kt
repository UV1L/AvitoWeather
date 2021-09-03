package com.example.avitoweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.avitoweather.ui.fragments.MainPageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_container, MainPageFragment.instance())
            .addToBackStack(null)
            .commit()
    }
}