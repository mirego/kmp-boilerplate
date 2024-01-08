package com.mirego.kmp.boilerplate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mirego.kmp.boilerplate.viewmodels.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodels.lifecycleViewModel
import com.mirego.kmp.boilerplate.views.ExampleView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ViewModelFactory()

        setContent {
            ExampleView(
                viewModel = lifecycleViewModel {
                    viewModelFactory.example()
                }
            )
        }
    }
}
