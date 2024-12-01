package com.app.exploria.presentation.ui.features.search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.viewModel.DestinationViewModel

@Composable
fun SearchScreen(navController: NavController) {
    val destinationViewModel: DestinationViewModel = hiltViewModel()
    val listDestination by destinationViewModel.listDestinationData.collectAsState()
    val isLoading by destinationViewModel.isLoading.collectAsState()
    val errorMessage by destinationViewModel.errorMessage.collectAsState()

    var query by remember { mutableStateOf("") }
    var searchTriggered by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                CustomHeaderTitle(
                    onClick = { navController.popBackStack() },
                    title = "Cari Destinasi"
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Cari destinasi") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    searchTriggered = true
                    if (query.isNotEmpty()) {
                        destinationViewModel.searchDestination(query)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cari")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("Memuat destinasi...")
                }
            } else if (!errorMessage.isNullOrEmpty()) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
                }
            } else if (searchTriggered && !listDestination.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listDestination ?: emptyList()) { destination ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = destination.name ?: "Nama tidak tersedia",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            modifier = Modifier.clickable {
                                // Navigasi ke DetailScreen dengan ID
                                navController.navigate("detail/${destination.id}")
                            }
                        )
                    }
                }
            } else if (searchTriggered) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("Tidak ada destinasi ditemukan.")
                }
            }
        }
    }
}

