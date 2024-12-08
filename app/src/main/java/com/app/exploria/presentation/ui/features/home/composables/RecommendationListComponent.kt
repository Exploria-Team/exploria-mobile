package com.app.exploria.presentation.ui.features.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.exploria.presentation.ui.features.common.ItemList
import com.app.exploria.presentation.viewModel.ModelViewModel

@Composable
fun RecomendationListComponent(navController: NavController, modifier: Modifier = Modifier) {
    val modelViewModel: ModelViewModel = hiltViewModel()
    val modelData = modelViewModel.normalModelData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        modelViewModel.fetchNormalModel()
    }

    Column {
        Text(
            text = "Rekomendasi untuk mu",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(modelData.itemCount) { index ->
                modelData[index]?.let { data ->
                    ItemList(
                        navController = navController,
                        destination = data,
                        getId = { it?.id },
                        getName = { it?.name },
                        getPhotoUrls = { it?.photos }
                    )
                }
            }
        }
    }
}
