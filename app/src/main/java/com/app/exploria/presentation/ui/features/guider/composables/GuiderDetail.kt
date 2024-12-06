package com.app.exploria.presentation.ui.features.guider.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.data.remote.response.GetTourGuideByIdData
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.TourGuideViewModel
import com.app.exploria.utils.openWhatsApp

@Composable
fun GuiderDetailScreen(id: String?, navController: NavController) {
    val context = LocalContext.current
    val tourGuideViewModel: TourGuideViewModel = hiltViewModel()
    val guideData = tourGuideViewModel.selectedTourGuide.collectAsState()
    val loading = tourGuideViewModel.isLoading.collectAsState()
    val errorMessage = tourGuideViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        if (id != null) {
            tourGuideViewModel.getTourGuideById(id)
        }
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(vertical = 16.dp)) {
                CustomHeaderTitle(
                    onClick = { navController.navigate(Screen.Guide.route) },
                    title = "Guider"
                )
            }
        },
        bottomBar = {
            NavigationBottom(navController)
        }
    ) { innerPadding: PaddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            when {
                loading.value -> LoadingState()
                errorMessage.value?.isNotEmpty() == true -> ErrorState(errorMessage.value)
                else -> guideData.value?.let { tourGuide ->
                    DetailContent(tourGuide, context)
                }
            }
        }
    }
}

@Composable
fun LoadingState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Memuat Data...", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ErrorState(errorMessage: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Terjadi Kesalahan: $errorMessage",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun DetailContent(
    tourGuide: GetTourGuideByIdData,
    context: android.content.Context,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileSection(tourGuide)
            Spacer(modifier = Modifier.height(16.dp))
            DescriptionSection(tourGuide)
        }

        BookingSection(
            tourGuide = tourGuide,
            context = context,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ProfileSection(tourGuide: GetTourGuideByIdData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profiledefault),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(130.dp)
                .clip(CircleShape)
        )
        Text(
            tourGuide.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (tourGuide.verified) {
                Text(
                    "Verified",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.verified),
                    contentDescription = "Verified",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            tourGuide.location,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            )
        )
    }
}

@Composable
fun DescriptionSection(tourGuide: GetTourGuideByIdData) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 30.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 22.dp, horizontal = 16.dp)
        ) {
            Text(
                "Deskripsi",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                tourGuide.bio,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()
            Spacer(modifier = Modifier.padding(top = 4.dp))

            Text(
                "Bahasa yang digunakan",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                "Bahasa Indonesia dan Inggris",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider()
            Spacer(modifier = Modifier.padding(top = 4.dp))

            Text(
                "Kategori",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                tourGuide.category,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BookingSection(
    tourGuide: GetTourGuideByIdData,
    context: android.content.Context,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Booking ${tourGuide.name} menjadi pemandu mu",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Mulai dari ${tourGuide.price}rb/hari",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            CustomButton(
                text = "Jadikan Guidemu!",
                width = 378,
                onClick = { openWhatsApp(context, tourGuide.waNumber) }
            )
        }
    }
}
