package com.app.exploria.presentation.ui.features.planning.composables

import SimpleDateRangePicker
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomTextField
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.TravelPlanViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlanningScreen(navController: NavController) {
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val showRangeModal = remember { mutableStateOf(false) }
    val selectedDateRange = remember { mutableStateOf<Pair<Long?, Long?>>(null to null) }
    val totalDays = remember { mutableStateOf(0) }
    val travelPlanViewModel: TravelPlanViewModel = hiltViewModel()
    val postPlanResult = travelPlanViewModel.postPlanResult.collectAsState()

    // Handle result from postPlan
    LaunchedEffect(postPlanResult.value) {
        postPlanResult.value?.let { planData ->
            if (planData.id != null) {
                navController.navigate("${Screen.SecondPlan.route}/${planData.id}")
            }
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBottom(navController)
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
                    text = "Mau Liburan berapa hari?",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Kami akan menyesuaikan tempat",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "yang wajib kamu kunjungi",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomTextField(
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        label = "Name Plan",
                        icon = Icons.Default.Create
                    )
                    SimpleDateRangePicker(showRangeModal, selectedDateRange, totalDays)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomButton(
                    text = "Selanjutnya",
                    width = 378,
                    height = 64,
                    onClick = {
                        val startDate = selectedDateRange.value.first
                        val endDate = selectedDateRange.value.second

                        if (nameState.value.text.isNotBlank() && startDate != null && endDate != null) {
                            val formattedStartDate = SimpleDateFormat("yyyy-MM-dd").format(Date(startDate))
                            val formattedEndDate = SimpleDateFormat("yyyy-MM-dd").format(Date(endDate))

                            travelPlanViewModel.postPlan(
                                name = nameState.value.text,
                                startDate = formattedStartDate,
                                endDate = formattedEndDate
                            )

                            navController.navigate(Screen.CreatePlan.route)
                        } else {
                            travelPlanViewModel.setErrorMessage("Semua kolom wajib diisi.")
                        }
                    }
                )
            }
        }
    }
}
