package com.app.exploria.presentation.ui.features.detail.composables

import Toolbar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.exploria.R
import com.example.compose.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            Toolbar()
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
                BodyDetailComponent(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AppTheme {
        DetailScreen()
    }
}
