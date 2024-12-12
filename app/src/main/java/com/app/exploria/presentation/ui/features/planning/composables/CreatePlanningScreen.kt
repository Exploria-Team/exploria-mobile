package com.app.exploria.presentation.ui.features.planning.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.features.planning.common.AddDestinationButton
import com.app.exploria.presentation.ui.features.planning.common.PlanCard
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.TravelPlanViewModel

@Composable
fun CreatePlanningScreen(navController: NavController, user: UserModel?) {
    val travelPlanViewModel: TravelPlanViewModel = hiltViewModel()
    val plan = travelPlanViewModel.plans.collectAsState().value
    val loading = travelPlanViewModel.isLoading.collectAsState()
    val error = travelPlanViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        travelPlanViewModel.getPlans()
    }

    Scaffold(
        bottomBar = {
            NavigationBottom(navController = navController, user = user)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Daftar Rencana",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Rencana yang telah kamu buat",
                    style = MaterialTheme.typography.titleMedium
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
                    LazyColumn(modifier = Modifier.padding(16.dp)) {

                        items(plan.size) { item ->
                            plan[item].let {
                                PlanCard(navController, it.name, it.id)
                            }
                        }
                        item {
                            AddDestinationButton(onClick = { navController.navigate(Screen.Plan.route) }, text = "Tambah Rencana")
                        }
                    }

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
            }
        }
    }
}