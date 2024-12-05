package com.app.exploria.presentation.ui.features.guider.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.components.EmptyView
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.TourGuideViewModel

@Composable
fun GuideListScreen(navController: NavController) {
    val tourGuideViewModel: TourGuideViewModel = hiltViewModel()
    val guideData = tourGuideViewModel.allTourGuide.collectAsState()
    val loading = tourGuideViewModel.isLoading.collectAsState()
    val errorMessage = tourGuideViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        tourGuideViewModel.loadAllTourGuides()
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                CustomHeaderTitle(
                    onClick = { navController.navigate(Screen.Home.route) },
                    title = "Guide List"
                )
            }
        },
        bottomBar = {
            NavigationBottom(navController)
        }
    ) { innerPadding: PaddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            if (errorMessage.value != null) {
                EmptyView(message = errorMessage.value!!, isError = true)
            } else if (loading.value == true) {
                EmptyView("Tunggu Sebentar")
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(guideData.value) { guide ->
                        GuideItemList(guideData = guide, navController = navController)
                    }
                }
            }
        }
    }
}
