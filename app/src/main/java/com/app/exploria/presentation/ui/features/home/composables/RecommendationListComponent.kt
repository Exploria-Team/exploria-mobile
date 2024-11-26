package com.app.exploria.presentation.ui.features.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.exploria.presentation.ui.features.common.ItemList

@Composable
fun RecomendationListComponent(recomendations: List<Int>, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Rekomendasi untuk mu",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp ,vertical = 8.dp)
        )

        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(recomendations) { recomendation ->
                ItemList(image = recomendation)
            }
        }
    }
}
