package com.app.exploria.presentation.ui.features.detail.composables

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.ReviewViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun ReviewFormScreen(destinationId: String?, navController: NavController) {
    var rating: Float by remember { mutableFloatStateOf(3f) }
    var reviewText: String by remember { mutableStateOf("") }
    var photoUri: Uri? by remember { mutableStateOf(null) }

    val reviewViewModel: ReviewViewModel = hiltViewModel()
    val submitReviewResult by reviewViewModel.submitReview.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val onPostReviewClicked = {
        if (reviewText.isBlank()) {
            // Harus di isi
        } else {
            reviewViewModel.submitReview(destinationId?.toInt() ?: 0, reviewText, rating, photoUri)
            navController.navigate("detail/${destinationId}")
        }
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            photoUri = uri
        }

    LaunchedEffect(submitReviewResult) {
        submitReviewResult?.let { result ->
            if (result.isSuccess) {
                snackbarHostState.showSnackbar("Review berhasil dikirim!")
            } else {
                snackbarHostState.showSnackbar("Gagal mengirim review: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                CustomHeaderTitle(
                    onClick = { navController.navigate(Screen.Home.route) },
                    title = "Review"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding: PaddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                item {
                    Text(
                        "Tambahkan Foto",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomButton(
                        text = "Pilih Foto",
                        onClick = { launcher.launch("image/*") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    photoUri?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Preview Foto",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .border(1.dp, Color.Gray)
                        )
                    }
                }

                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Seberapa Puas Anda?",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RatingBar(
                            value = rating,
                            style = RatingBarStyle.Fill(),
                            onValueChange = {
                                rating = it
                            },
                            onRatingChanged = {
                                Log.d("TAG", "onRatingChanged: $it")
                            }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Apakah Pengalaman anda Sustainable?",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "** Guidelines:\n" +
                                "• Beri penilaian pada kebersihan dan bebas sampah di\n" +
                                "  destinasi tersebut.\n" +
                                "• Evaluasi: tempat sampah mudah diakses tersedia.\n" +
                                "• Evaluasi fasilitas daur ulang dan sistem pengelolaan\n" +
                                "  sampah di lokasi.",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Tulis Review Anda:",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    BasicTextField(
                        value = reviewText,
                        onValueChange = { reviewText = it },
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .border(1.dp, Color.Gray)
                            .padding(start = 8.dp),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.TopStart
                            ) {
                                if (reviewText.isEmpty()) {
                                    Text(
                                        "Masukkan ulasan Anda di sini",
                                        style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    CustomButton(
                        text = "Post Review",
                        width = 378,
                        onClick = onPostReviewClicked
                    )
                }
            }
        }
    }
}
