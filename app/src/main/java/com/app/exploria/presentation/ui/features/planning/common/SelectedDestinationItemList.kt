package com.app.exploria.presentation.ui.features.planning.common

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponseItem
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SelectedDestinationItemList(
    navController: NavController? = null,
    planItem: GetPlanDestinationByIdResponseItem
) {
    val color = MaterialTheme.colorScheme
    val font = MaterialTheme.typography
    val item = planItem.destination

    Box(
        modifier = Modifier
            .border(2.dp, color.outlineVariant, RoundedCornerShape(20.dp))
            .background(color.surfaceContainer, RoundedCornerShape(20.dp))
            .clickable {
                navController?.navigate("detail/${planItem.destinationId}")
            }
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item.photoUrls.let {
                GlideImage(
                    imageModel = it[0],
                    contentDescription = item.name,
                    modifier = Modifier.size(100.dp)
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
                        text = item.name ?: "Unknown Name",
                        style = font.titleLarge,
                        color = color.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = "Biaya masuk: Rp.${
                        if (item.entryFee == null || item.entryFee == 0) "-" else item.entryFee
                    }",
                    style = font.titleMedium,
                    color = color.onSurfaceVariant
                )
                Text(
                    text = "Durasi: ${
                        if (item.visitDurationMinutes == null || item.visitDurationMinutes == 0) "∞"
                        else "${item.visitDurationMinutes} menit"
                    }",
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
                            value = if (item.averageRating == 0f) 0f else item.averageRating ?: 0f,
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

