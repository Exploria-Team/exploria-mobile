package com.app.exploria.presentation.ui.features.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomButtonNavigation(
    icon: ImageVector,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color.surface)
            .graphicsLayer(
                shadowElevation = 3f,
                shape = CircleShape,
                clip = true
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Navigation Button",
            tint = iconColor
        )
    }
}
