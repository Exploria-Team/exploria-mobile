package com.app.exploria.presentation.ui.features.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomHeaderTitle(onClick: () -> Unit, title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp) // Tinggi standar topBar
            .padding(horizontal = 16.dp), // Padding kiri-kanan
        contentAlignment = Alignment.Center // Penyelarasan konten ke tengah
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol Back
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                CustomButtonNavigation(
                    icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                )
            }
        }

        // Judul berada tepat di tengah
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
