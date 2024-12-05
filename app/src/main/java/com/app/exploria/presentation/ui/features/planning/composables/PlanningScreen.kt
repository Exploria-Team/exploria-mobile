package com.app.exploria.presentation.ui.features.planning.composables

import SimpleDateRangePicker
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.features.planning.common.AddDestinationButton
import com.app.exploria.presentation.ui.features.planning.common.PlanCard
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlanningScreen(navController: NavController) {

    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
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
                SimpleDateRangePicker()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomButton(text = "Selanjutnya", width = 378, height = 64, onClick = {
                    navController.navigate(
                        Screen.SecondPlan.route
                    )
                })
            }
        }
    }
}

@Composable
fun SecondPlanningScreen(navController: NavController) {
    Scaffold(
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
                    text = "Pilih dan Atur perjalananmu?",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))
                AddDestinationButton(onClick = {navController.navigate(Screen.SelectDestination.route)})
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomButton(
                    text = "Simpan",
                    width = 378,
                    height = 64,
                    onClick = { navController.navigate(Screen.FinalPlan.route) })
            }
        }
    }
}

@Composable
fun FinalPlanningScreen(navController: NavController) {
    Scaffold(
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
                PlanCard(planName = "Rencana 1")
                AddDestinationButton(onClick = {navController.navigate(Screen.Plan.route)})
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

@Preview(showBackground = true)
@Composable
fun FirstPlanningScreenPreview() {
    AppTheme {
    }
}

