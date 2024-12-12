package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.data.remote.response.GetDestinationByIdResponse

@Composable
fun BodyDetailComponent(navController: NavController, destinationData: GetDestinationByIdResponse, userModel: UserModel?) {

    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Deskripsi", "Ulasan", "Peta")

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        item {
            Text(
                text = destinationData.data.name ?: "Nama tidak tersedia",
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
        0 -> DetailFragment(data = destinationData.data)
        1 -> ReviewFragment(id = destinationData.data.id, navController = navController, userModel = userModel)
        2 -> MapFragment(lat = destinationData.data.lat, lon = destinationData.data.lon)
    }
}
