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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.navigation.Screen
import com.example.compose.AppTheme

@Composable
fun GuiderDetailScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                CustomHeaderTitle(
                    onClick = { navController?.navigate(Screen.Home.route) },
                    title = "Guider"
                )
            }
        },
        bottomBar = {
            navController?.let { NavigationBottom(it) }
        }
    ) { innerPadding: PaddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .size(130.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            "Dinda Waterson",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            "Personal Guider",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        )

                        Text(
                            "Bali, Denpasar",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp)) // For spacing

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
                                "Dinda, personal guider Bali, siap menemani Anda menjelajah destinasi unik, budaya lokal, dan kuliner khas. Dengan ramah dan profesional, ia pastikan liburan Anda aman, santai, dan penuh kenangan.",
                                style = TextStyle(
                                    fontSize = 16.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(32.dp)) // Memberikan jarak antar elemen

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

                            Spacer(modifier = Modifier.padding(top = 16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(onClick = { }) {
                                    Text("Pantai")
                                }
                                Button(onClick = { }) {
                                    Text("Cagar Alam")
                                }
                                Button(onClick = { }) {
                                    Text("Hiking")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                ) {
                    Column {
                        Text(
                            "Booking Dinda menjadi pemandu mu",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                        )

                        Text(
                            "Mulai dari 300rb/hari",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)

                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CustomButton(text = "Jadikan Guidemu!", width = 378)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        GuiderDetailScreen()
    }
}
