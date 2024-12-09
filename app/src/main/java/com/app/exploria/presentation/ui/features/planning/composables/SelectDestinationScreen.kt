package com.app.exploria.presentation.ui.features.planning.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
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
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.exploria.presentation.ui.components.EmptyView
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
    val distanceModelData = viewModel.distanceModelData.collectAsState().value

    val selectedItem = remember { mutableStateOf<Int?>(null) }

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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isNormalModel) {
                items(normalModelData.itemCount) { index ->
                    val destination = normalModelData[index]
                    SelectableItemList(
                        destination = destination,
                        isSelected = selectedItem.value == destination?.id,
                        onSelect = { item ->
                            val id = item?.id
                            selectedItem.value = if (selectedItem.value == id) null else id
                        },
                        getId = { it?.id },
                        getName = { it?.name },
                        getPhotoUrls = { it?.photos }
                    )
                }
            } else {
                if (distanceModelData.size > 0) {
                    items(distanceModelData.size) { index ->
                        val destination = distanceModelData[index]
                        SelectableItemList(
                            destination = destination,
                            isSelected = selectedItem.value == destination?.id,
                            onSelect = { item ->
                                val id = item?.id
                                selectedItem.value = if (selectedItem.value == id) null else id
                            },
                            getId = { it?.id },
                            getName = { it?.name },
                            getPhotoUrls = { it?.photos }
                        )
                    }
                } else {
                    item {
                        EmptyView("Sedang mencarikan rekomendasi")
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(64.dp),
            contentAlignment = Alignment.BottomCenter
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


