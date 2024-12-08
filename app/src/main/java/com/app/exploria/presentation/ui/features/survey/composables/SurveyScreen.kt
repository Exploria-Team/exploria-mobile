package com.app.exploria.presentation.ui.features.survey.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.CategoryViewModel
import com.app.exploria.presentation.viewModel.ProfileViewModel

@Composable
fun SurveyScreen(navController: NavController) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val categories = categoryViewModel.categories.collectAsState(initial = emptyList())

    val selectedCategories = remember { mutableStateListOf<Int>() }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(top = 16.dp)) {
                CustomHeaderTitle(
                    onClick = { navController.navigate(Screen.Home.route) },
                    title = "Preferensi"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Pilih 5 kategori sebelum memulai",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Untuk menyesuaikan dengan preferensi mu",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.value.chunked(2)) { rowCategories ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowCategories.forEach { category ->
                            val isSelected = selectedCategories.contains(category.id)
                            CategoryItem(
                                category = category.name,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) {
                                        selectedCategories.remove(category.id)
                                    } else if (selectedCategories.size < 5) {
                                        selectedCategories.add(category.id)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    profileViewModel.postPreferences(selectedCategories)

                    navController.navigate(Screen.Home.route)
                },
                enabled = selectedCategories.size == 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = CircleShape
            ) {
                Text(
                    text = if (selectedCategories.size < 5) {
                        "Pilih ${5 - selectedCategories.size} kategori lagi"
                    } else {
                        "Konfirmasi Pilihan"
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick() },
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.titleMedium,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}
