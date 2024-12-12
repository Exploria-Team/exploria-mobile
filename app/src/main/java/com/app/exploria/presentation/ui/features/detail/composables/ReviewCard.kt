package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.exploria.R
import com.app.exploria.data.remote.response.ReviewsItem
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReviewCard(review: ReviewsItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                val user = review.user
                val profilePictureUrl = user?.profilePictureUrl ?: ""
                val userName = user?.name ?: "Unknown"

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (profilePictureUrl.isNotEmpty()) {
                        GlideImage(
                            imageModel = profilePictureUrl,
                            contentDescription = userName,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.profiledefault),
                            contentDescription = "Default Reviewer Image",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.secondary)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            RatingBar(
                                value = review.rating?.toFloat() ?: 0f,
                                style = RatingBarStyle.Fill(),
                                size = 16.dp,
                                spaceBetween = 1.dp,
                                onValueChange = {},
                                onRatingChanged = {}
                            )
                        }
                        Text(
                            text = userName,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = review.reviewText ?: "No comment",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            if (!review.reviewPhotoUrl.isNullOrEmpty()) {
                ZoomableImageOnClick(
                    imageUrl = review.reviewPhotoUrl,
                    contentDescription = review.user?.name ?: "Reviewer Photo"
                )
            }
        }
    }
}

@Composable
fun ZoomableImageOnClick(imageUrl: String, contentDescription: String) {
    var isZoomed by remember { mutableStateOf(false) }
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    if (isZoomed) {
        // Zoomable Image
        GlideImage(
            imageModel = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        offsetX += pan.x
                        offsetY += pan.y
                    }
                }
                .graphicsLayer(
                    scaleX = maxOf(1f, scale),
                    scaleY = maxOf(1f, scale),
                    translationX = offsetX,
                    translationY = offsetY
                )
                .clickable { isZoomed = false },
            contentScale = ContentScale.Crop
        )
    } else {
        GlideImage(
            imageModel = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { isZoomed = true },
            contentScale = ContentScale.Crop
        )
    }
}
