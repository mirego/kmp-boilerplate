package com.mirego.kmp.boilerplate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mirego.kmp.boilerplate.views.example.ExampleView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = AppBootstrapper().viewModelFactory

        setContent {
            ExampleView(viewModel = viewModelFactory.exampleViewModel(lifecycleScope))
        }
    }
}
