package com.rajit.composedestinationsdemo.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rajit.composedestinationsdemo.model.Person
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(
    person: Person,
    navigator: DestinationsNavigator
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(shape = CircleShape) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(person.profileImgURL)
                    .crossfade(true)
                    .crossfade(1000)
                    .build(),
                contentDescription = "Person Profile Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "${person.name}, ${person.age}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = person.countryOfResidence, fontSize = 13.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    "Bio",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = person.bio)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    "Complete Address",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = person.address)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Social Links",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                SocialLinksRow(person.socialLinks)
            }
        }

    }
}

@Composable
fun SocialLinksRow(socialLinks: List<String>) {

    val context = LocalContext.current

    Row {
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTihTjCPyRMsJgWrDbBwB0NULJmCfvQAFqyNOI0_vlIXw&s",
            contentDescription = "LinkedIn Image Link",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(socialLinks[0]))
                    context.startActivity(intent)
                }
        )

        Spacer(Modifier.width(8.dp))

        AsyncImage(
            model = "https://seeklogo.com/images/I/instagram-new-2016-logo-4773FE3F99-seeklogo.com.png",
            contentDescription = "Instagram Image Link",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(socialLinks[1]))
                    context.startActivity(intent)
                }
        )

        Spacer(Modifier.width(8.dp))

        AsyncImage(
            model = "https://cdn.pixabay.com/photo/2021/08/10/17/03/facebook-6536473_960_720.png",
            contentDescription = "Facebook Image Link",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(socialLinks[2]))
                    context.startActivity(intent)
                }
        )
    }
}
