package com.app.exploria.presentation.ui.features.planning.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.data.remote.response.GetPlanDestinationByIdDestination
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponseItem
import com.app.exploria.presentation.ui.theme.AppTheme
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun SelectedDestinationItemList(
    navController: NavController? = null,
    planItem: GetPlanDestinationByIdResponseItem? = defaultPlanItem()
) {
    val color = MaterialTheme.colorScheme
    val font = MaterialTheme.typography
    val item = planItem?.destination

    Box(
        modifier = Modifier
            .border(2.dp, color.outlineVariant, RoundedCornerShape(20.dp))
            .background(color.surfaceContainer, RoundedCornerShape(20.dp))
            .clickable {
                navController?.navigate("detail/${planItem?.destinationId}")
            }
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!item?.name.isNullOrEmpty()) {
                Image(
                    painter = painterResource(R.drawable.img),
                    contentDescription = item?.name,
                    modifier = Modifier.size(100.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .background(color.surfaceVariant, RoundedCornerShape(10.dp))
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item?.name ?: "Unknown Name",
                        style = font.titleLarge,
                        color = color.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = "Biaya masuk: ${item?.entryFee ?: "Not Available"}",
                    style = font.titleMedium,
                    color = color.onSurfaceVariant
                )
                Text(
                    text = "Durasi: ${item?.visitDurationMinutes ?: "Not Available"} menit",
                    style = font.titleMedium,
                    color = color.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(color.secondary)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        RatingBar(
                            value = item?.averageRating ?: 0f,
                            style = RatingBarStyle.Fill(),
                            size = 16.dp,
                            spaceBetween = 1.dp,
                            onValueChange = {},
                            onRatingChanged = {}
                        )
                    }
                }
            }
        }
    }
}

fun defaultPlanItem(): GetPlanDestinationByIdResponseItem {
    return GetPlanDestinationByIdResponseItem(
        date = "2024-12-09",
        destination = GetPlanDestinationByIdDestination(
            entryFee = "20,000 IDR",
            visitDurationMinutes = 90,
            averageRating = 4.5f,
            name = "Rainbow Garden",
            description = "A beautiful garden with colorful flowers.",
            lon = 107.618914,
            id = 332,
            cityId = 3,
            lat = -6.8179514
        ),
        planId = "samplePlanId",
        id = "sampleDestinationId",
        destinationId = 332
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        SelectedDestinationItemList()
    }
}
