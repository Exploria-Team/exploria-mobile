package com.app.exploria.presentation.ui.features.home.composables

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.NavigationBottom

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    Scaffold(
        topBar = {
            HeaderComponent(navController = navController)
        },
        bottomBar = {
            NavigationBottom(navController = navController)
        }
    ) { innerPadding: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface
            ) {
                BoxWithConstraints(
                    modifier = Modifier.fillMaxSize()

                ) {
                    val maxHeight = this.maxHeight
                    val listState = rememberLazyListState()
                    val isDestinationListVisible = remember { mutableStateOf(false) }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        state = listState
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 50.dp,
                                            bottomEnd = 50.dp
                                        )
                                    )
                                    .background(color = MaterialTheme.colorScheme.primary)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    BorderComponent(
                                        images = listOf(
                                            R.drawable.img2,
                                            R.drawable.img2,
                                            R.drawable.img2,
                                        )
                                    )
                                    PromoCardComponent(navController)
                                }
                            }
                        }

                        item {
                            RecomendationListComponent(
                                navController,
                                recomendations = listOf(
                                    R.drawable.img,
                                    R.drawable.img2,
                                    R.drawable.img2,
                                    R.drawable.img2
                                ),
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                        }

                        item {
                            LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
                                isDestinationListVisible.value =
                                    listState.firstVisibleItemIndex >= 2
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(maxHeight)
                            ) {
                                DestinationsListComponent(
                                    navController,
                                    recomendations = listOf(
                                        R.drawable.img,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2,
                                        R.drawable.img2
                                    ),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Konfirmasi Keluar") },
            text = { Text("Apakah Anda yakin ingin keluar dari aplikasi?") },
            confirmButton = {
                TextButton(onClick = { activity?.finish() }) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("Tidak")
                }
            }
        )
    }
}