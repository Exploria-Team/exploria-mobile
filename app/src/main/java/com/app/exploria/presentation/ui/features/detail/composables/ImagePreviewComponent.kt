package com.app.exploria.presentation.ui.features.detail.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.app.exploria.data.remote.response.GetDestinationByIdResponse
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.absoluteValue

@Composable
fun ImagePreviewComponent(destination: GetDestinationByIdResponse, modifier: Modifier = Modifier) {
    val photoUrls = destination.data.photoUrls

    val pagerState = rememberPagerState(pageCount = { photoUrls.size })

    HorizontalPager(state = pagerState) { page ->
        Card(
            modifier = modifier
                .height(270.dp)
                .fillMaxWidth()
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier.fillMaxSize()
            ) {
                photoUrls.let { photoUrls ->
                    GlideImage(
                        imageModel = photoUrls[page],
                        contentDescription = "Carousel Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
