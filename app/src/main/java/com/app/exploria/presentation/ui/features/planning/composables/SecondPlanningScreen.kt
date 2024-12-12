package com.app.exploria.presentation.ui.features.planning.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.planning.common.AddDestinationButton
import com.app.exploria.presentation.ui.features.planning.common.SelectedDestinationItemList
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.TravelPlanViewModel

@Composable
fun SecondPlanningScreen(
    navController: NavController,
    planId: String? = null,
    travelPlanViewModel: TravelPlanViewModel = hiltViewModel()
) {
    val planDetails by travelPlanViewModel.planDetails.collectAsState()
    val loading = travelPlanViewModel.isLoading.collectAsState()
    val error = travelPlanViewModel.errorMessage.collectAsState()

    LaunchedEffect(planId) {
        planId?.let {
            travelPlanViewModel.fetchPlanDetails(it)
        }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Destinasi dalam Perjalananmu:",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    items(planDetails.size) { index ->
                        planDetails[index].let {
                            SelectedDestinationItemList(navController, it)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    item {
                        AddDestinationButton(onClick = {
                            val lastPlanId = planDetails.lastOrNull()?.destinationId
                            navController.navigate(
                                if (lastPlanId != null) {
                                    "select_destination_screen/$planId/$lastPlanId"
                                } else {
                                    "select_destination_screen/$planId"
                                }
                            )
                        }, text = "Tambah Tempat")
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomButton(
                    text = "Simpan Perjalanan",
                    onClick = {
                        navController.navigate(Screen.CreatePlan.route)
                    }
                )
            }
        }
    }
}
