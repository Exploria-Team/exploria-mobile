package com.app.exploria.presentation.ui.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.exploria.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderComponent() {
    val query = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SearchBar(
            query = query.value,
            onQueryChange = { query.value = it },
            onSearch = { expanded.value = false },
            active = expanded.value,
            onActiveChange = { expanded.value = it },
            placeholder = { Text("Mau kemana hari ini") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            modifier = Modifier
                .width(254.dp)
                .height(56.dp)
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                repeat(4) { idx ->
                    val resultText = "Suggestion $idx"
                    ListItem(
                        headlineContent = { Text(resultText) },
                        supportingContent = { Text("Additional info") },
                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .clickable {
                                query.value = resultText
                                expanded.value = false
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }

        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(40.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.avatar13),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
        )
    }
}
