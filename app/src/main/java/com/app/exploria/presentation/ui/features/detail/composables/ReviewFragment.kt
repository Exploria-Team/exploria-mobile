
package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.ReviewViewModel

@Composable
fun ReviewFragment(navController: NavController, id: Int, userModel: UserModel?) {
    val reviewViewModel: ReviewViewModel = hiltViewModel()

    val reviews = reviewViewModel.reviewData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        reviewViewModel.getReviews(id)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (userModel?.isLogin == true) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Berikan ulasan anda",
                        style = MaterialTheme.typography.titleLarge
                    )
                    CustomButton(
                        "review",
                        width = 100,
                        textStyle = MaterialTheme.typography.titleMedium,
                            onClick = { navController.navigate("reviewForm/${id}") }
                    )
                }
            }
        }

        if (reviews.loadState.refresh is LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        if (reviews.itemCount > 0) {
            items(reviews.itemCount) { index ->
                reviews[index]?.let { review ->
                    ReviewCard(review = review)
                }
            }
        } else {
            item {
                Text("No reviews available.")
            }
        }


        if (reviews.loadState.append is LoadState.Error) {
            item {
                Text("Error loading reviews.")
            }
        }
    }
}

