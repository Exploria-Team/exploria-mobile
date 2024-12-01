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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.viewModel.DestinationViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(id: String?, navController: NavController) {
    val destinationViewModel: DestinationViewModel = hiltViewModel()
    val destinationData by destinationViewModel.destinationData.collectAsState()

    LaunchedEffect(id) {
        id?.toIntOrNull()?.let {
            destinationViewModel.getDestinationById(it)
        }
    }

    println("Destination Data: ${destinationData}")


    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Toolbar(navController)
            }
        }
    ) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            if (destinationData != null) {
                Column(modifier = Modifier.fillMaxSize()) {
                    ImagePreviewComponent(
                        images = listOf(
                            R.drawable.img2,
                            R.drawable.img2,
                            R.drawable.img2,
                        )
                    )
                    BodyDetailComponent(destinationData!!)
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Loading data...", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
