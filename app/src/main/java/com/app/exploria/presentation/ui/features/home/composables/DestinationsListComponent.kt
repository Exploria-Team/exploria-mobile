package com.app.exploria.presentation.ui.features.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.app.exploria.data.remote.response.AllDestinationsItem
import com.app.exploria.presentation.ui.features.common.ItemList

@Composable
fun DestinationsListComponent(
    navController: NavController,
    modifier: Modifier = Modifier,
    destination: LazyPagingItems<AllDestinationsItem>,
    ) {
    Column {
        Text(
            text = "Tempat mungkin kamu suka",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(destination.itemCount) { item ->
                ItemList(navController, destination = destination[item])
            }
        }
    }
}
