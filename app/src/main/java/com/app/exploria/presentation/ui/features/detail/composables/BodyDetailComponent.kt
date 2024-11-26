package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BodyDetailComponent(navController: NavController?) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Deskripsi", "Ulasan", "Peta")

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            Text(
                text = "Pantai Kuta - Bali",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                tabs.forEachIndexed { index, title ->
                    Text(
                        text = title,
                        modifier = Modifier
                            .clickable { selectedTab = index }
                            .padding(16.dp),
                        fontWeight = if (index == selectedTab) FontWeight.Bold else FontWeight.Normal,
                    )
                }
            }
            HorizontalDivider()
        }
    }

    when (selectedTab) {
        0 -> DetailFragment()
        1 -> UlasanFragment()
        2 -> PetaFragment()
    }
}

@Composable
fun DetailFragment() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Pantai Kuta terletak di Kecamatan Kuta, Kabupaten Badung, Bali, sekitar 10 km dari Kota Denpasar dan hanya 15 menit berkendara dari Bandara Internasional Ngurah Rai. Pantai ini memiliki garis pantai sepanjang sekitar 2,5 kilometer dengan pasir putih lembut dan ombak yang ideal untuk berselancar. Pantai Kuta juga dikenal dengan pemandangan matahari terbenam yang menakjubkan, menjadikannya tempat favorit bagi wisatawan untuk menikmati senja",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Aktivitas Wisata",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun UlasanFragment() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Aktivitas Wisata",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {},
                    enabled = true
                ) {
                    Text("Tulis Ulasan")
                }
            }
        }
        items(5) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ReviewCard()
            }
        }
    }
}

@Composable
fun PetaFragment() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Jl. Pantai Kuta No.32, Legian, Badung, Kabupaten Badung, Bali",
            fontSize = 16.sp
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 16.dp)
        ) {
            Text(text = "[Map Placeholder]", modifier = Modifier.align(Alignment.Center))
        }
    }
}