package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.exploria.data.remote.response.GetDestinationByIdData

@Composable
fun DetailFragment(data: GetDestinationByIdData) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Detail Informasi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            InformationTable(data)
        }

        item {
            Text(
                text = "Deskripsi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Text(
                text = data.description ?: "Deskripsi tidak tersedia",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Composable
fun InformationTable(data: GetDestinationByIdData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TableRow(label = "Nama Tempat", value = data.name)
        TableRow(label = "Kota", value = data.city)
        TableRow(label = "Biaya Masuk", value = "Rp.${data.entryFee}")
        TableRow(label = "Durasi Kunjungan", value = "${data.visitDurationMinutes} menit")
        TableRow(label = "Rating Rata-rata", value = "${data.averageRating} ⭐")
    }
}

@Composable
fun TableRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
