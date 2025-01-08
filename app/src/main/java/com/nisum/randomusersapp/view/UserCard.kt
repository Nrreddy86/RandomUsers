package com.nisum.randomusersapp.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nisum.randomusersapp.model.User

@Composable
fun UserCard(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
                    .align(Alignment.CenterVertically)
            ) {
                AsyncImage(
                    model = user.picture.medium,
                    contentDescription = "User Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                Text(
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    text = "${user.name.title}. ${user.name.first} ${user.name.last}",
                    color = Color.Black,
                    modifier = Modifier.padding(2.dp),
                    maxLines = 1
                )

                Text(
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    text = "Age: ${user.dob.age}",
                    color = Color.Black,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    text = "Phone: ${user.phone}",
                    color = Color.Black,
                    modifier = Modifier.padding(2.dp)
                )
            }

        }
    }
}

