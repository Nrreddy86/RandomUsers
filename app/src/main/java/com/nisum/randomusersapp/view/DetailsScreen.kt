package com.nisum.randomusersapp.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nisum.randomusersapp.viewModel.SharedViewModel

@Composable
fun DetailsScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val user = sharedViewModel.selectedUser

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(2.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White),
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .size(360.dp)
                            .clip(RectangleShape)
                    ) {
                        AsyncImage(
                            model = user?.picture?.large,
                            contentDescription = "User Profile Picture",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RectangleShape)
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        text = "Name: ${user?.name?.title} ${user?.name?.first} ${user?.name?.last}",
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )

                    Text(
                        fontFamily = FontFamily.Default,
                        fontSize = 16.sp,
                        text = "Email: ${user?.email}",
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        fontFamily = FontFamily.Default,
                        fontSize = 16.sp,
                        text = "Phone: ${user?.phone}",
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )

                    Text(
                        fontFamily = FontFamily.Default,
                        fontSize = 16.sp,
                        text = "Age: ${user?.dob?.age}",
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        fontFamily = FontFamily.Default,
                        fontSize = 16.sp,

                        text = "Address: ${user?.location?.street?.name}, " +
                                "${user?.location?.street?.number}, " +
                                "${user?.location?.street?.name}, " +
                                "${user?.location?.city}, " +
                                "${user?.location?.state}, " +
                                "${user?.location?.country}, " +
                                "${user?.location?.postcode}",
                        color = Color.Black,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
        BackHandler {
            navController.popBackStack()
        }
    }
}
