package com.app.exploria.presentation.ui.features.planning.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.app.exploria.presentation.ui.features.planning.common.PlanCard
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.TravelPlanViewModel

@Composable
fun SecondPlanningScreen(
    navController: NavController,
    planId: String? = null,
    travelPlanViewModel: TravelPlanViewModel = hiltViewModel()
) {
    val planDetails by travelPlanViewModel.planDetails.collectAsState()

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
                        fontWeight = FontWeight.Bold
                    )
                }
                items(planDetails.size) { index ->
                    planDetails[index].let {
                        PlanCard(planName = it.destination.name)
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    AddDestinationButton(onClick = {
                        val lastPlanId = planDetails.lastOrNull()?.destinationId
                        navController.navigate(
                            if (lastPlanId != null) {
                                "select_destination_screen/$planId/$lastPlanId"
                            } else {
                                "select_destination_screen/$planId"
                            }
                        )
                    })
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
