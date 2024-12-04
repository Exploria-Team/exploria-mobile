package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.exploria.presentation.viewModel.ReviewViewModel

@Composable
fun ReviewFragment(navController: NavController, id: Int) {
    val reviewViewModel: ReviewViewModel = hiltViewModel()

    val reviews = reviewViewModel.reviewData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        reviewViewModel.getReviews(id)
    }

    println("Reviews Data: ${reviews}")
    println("Review count: ${reviews.itemCount}")

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (reviews.loadState.refresh is LoadState.Loading) {
            item {
                CircularProgressIndicator()
            }
        }

        items(reviews.itemCount) { item ->
            reviews[item]?.let {
                ReviewCard(review = it)
            }
        }

        if (reviews.loadState.append is LoadState.Error) {
            item {
                Text("Error loading reviews.")
            }
        }
    }
}

