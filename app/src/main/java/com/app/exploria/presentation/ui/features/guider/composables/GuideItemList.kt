package com.app.exploria.presentation.ui.features.guider.composables

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.data.remote.response.TourGuidesItem

@Composable
fun GuideItemList(navController: NavController, guideData: TourGuidesItem) {
    val color = MaterialTheme.colorScheme
    val font = MaterialTheme.typography

    Box(
        modifier = Modifier
            .border(2.dp, color.outlineVariant, RoundedCornerShape(20.dp))
            .background(color.surfaceContainer, RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate("detailguide/${guideData.id}")
            }
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.avatar13),
                contentDescription = guideData.name,
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        guideData.name, style = font.titleLarge, color = color.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    if (guideData.verified) {
                        Image(
                            painter = painterResource(id = R.drawable.verified),
                            contentDescription = "verified",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Text(
                    guideData.location, style = font.titleMedium, color = color.onSurfaceVariant
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
                        Text(guideData.category, color = color.onSecondary, style = font.bodyLarge)
                    }
                }
            }
        }
    }
}