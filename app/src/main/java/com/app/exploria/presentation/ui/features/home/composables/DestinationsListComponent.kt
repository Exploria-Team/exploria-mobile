package com.app.exploria.presentation.ui.features.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.exploria.presentation.ui.features.common.ItemList
import com.app.exploria.presentation.viewModel.DestinationViewModel

@Composable
fun DestinationsListComponent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val destinationViewModel: DestinationViewModel = hiltViewModel()
    val destinations = destinationViewModel.destinations.collectAsLazyPagingItems()
    val loading = destinationViewModel.isLoading.collectAsState()
    val error = destinationViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        destinationViewModel.fetchDestinations()
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Tempat mungkin kamu suka",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
        if (loading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error.value?.isNotEmpty() == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = error.value!!, style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(destinations.itemCount) { index ->
                    destinations[index]?.let { destination ->
                        ItemList(navController, destination = destination)
                    }
                }
            }
        }
    }
}
