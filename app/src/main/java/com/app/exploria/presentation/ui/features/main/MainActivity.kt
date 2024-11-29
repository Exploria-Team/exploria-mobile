package com.app.exploria.presentation.ui.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.exploria.presentation.ui.navigation.AppNavigation
import com.app.exploria.presentation.viewModel.MainViewModel
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                AppNavigation(mainViewModel = mainViewModel)
            }
        }
    }
}