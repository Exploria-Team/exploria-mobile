package com.app.exploria.presentation.ui.features.detail.composables

import Toolbar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(id : String? = "satu", navController: NavController) {

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Toolbar(navController)
            }
        }
    ) { Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ImagePreviewComponent(
                    images = listOf(
                        R.drawable.img2,
                        R.drawable.img2,
                        R.drawable.img2,
                    )
                )
                BodyDetailComponent()

            }
        }
    }
}