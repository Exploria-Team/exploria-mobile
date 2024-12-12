package com.app.exploria.presentation.ui.features.planning.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.planning.common.SelectableItemList
import com.app.exploria.presentation.viewModel.ModelViewModel
import com.app.exploria.presentation.viewModel.TravelPlanViewModel

@Composable
fun SelectDestinationScreen(
    navController: NavController,
    planId: String,
    destinationId: Int?,
    viewModel: ModelViewModel = hiltViewModel(),
    travelPlanViewModel: TravelPlanViewModel = hiltViewModel()
) {
    val normalModelData = viewModel.normalModelData.collectAsLazyPagingItems()
    val distanceModelData = viewModel.distanceModelData.collectAsLazyPagingItems()
    val planDetails = travelPlanViewModel.planDetails.collectAsState().value

    val selectedItem = remember { mutableStateOf<Int?>(null) }
    val query = remember { mutableStateOf("") }

    val existingDestinationIds = planDetails?.map { it.destinationId } ?: emptyList()

    val isNormalModel = destinationId == null

    LaunchedEffect(destinationId) {
        if (isNormalModel) {
            viewModel.fetchNormalModel()
        } else {
            destinationId?.let { viewModel.fetchDistanceModel(it) }
        }
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(top = 16.dp)) {
                CustomHeaderTitle(
                    onClick = { navController.popBackStack() },
                    title = "Daftar Destinasi"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // Search Bar
            TextField(
                value = query.value,
                onValueChange = { query.value = it },
                placeholder = { Text("Cari destinasi") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    if (isNormalModel) {
                        items(normalModelData.itemSnapshotList.items.size) { index ->
                            val destination = normalModelData[index]
                            destination?.let {
                                if (it.name.contains(query.value, ignoreCase = true) && !existingDestinationIds.contains(it.id)) {
                                    SelectableItemList(
                                        destination = it,
                                        isSelected = selectedItem.value == it.id,
                                        onSelect = { item ->
                                            val id = item?.id
                                            selectedItem.value = if (selectedItem.value == id) null else id
                                        },
                                        getId = { it?.id },
                                        getName = { it?.name },
                                        getPhotoUrls = { it?.photos }
                                    )
                                }
                            }
                        }
                    } else {
                        items(distanceModelData.itemSnapshotList.items.size) { index ->
                            val destination = distanceModelData[index]
                            destination?.let {
                                if (it.name.contains(query.value, ignoreCase = true) && !existingDestinationIds.contains(it.id)) {
                                    SelectableItemList(
                                        destination = it,
                                        isSelected = selectedItem.value == it.id,
                                        onSelect = { item ->
                                            val id = item?.id
                                            selectedItem.value = if (selectedItem.value == id) null else id
                                        },
                                        getId = { it?.id },
                                        getName = { it?.name },
                                        getPhotoUrls = { it?.photos }
                                    )
                                }
                            }
                        }
                    }
                }

                if (normalModelData.loadState.append == LoadState.Loading || distanceModelData.loadState.append == LoadState.Loading) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CustomButton(
                    text = "Tambahkan",
                    onClick = {
                        if (selectedItem.value != null) {
                            travelPlanViewModel.uploadDestinationPlan(
                                date = "2024-12-09",
                                planId = planId,
                                destinationId = selectedItem.value!!
                            )
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}
