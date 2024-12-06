package com.hemanth.travelguide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemanth.travelguide.ui.theme.DestinationSearchActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen()
        }
    }


}

@Composable
fun TravelGuide(bgImage: Int, title: String, onClick: (title: String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick.invoke(title)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = painterResource(id = bgImage),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.white_50))
            )

            Text(
                text = title,
                color = Color.Black, // Set the base text color
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 12.dp, top = 8.dp)

            )
        }
    }

}


@Composable
fun HomeScreen() {

    val context = LocalContext.current as Activity

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.SkyBlue))
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_travelguide),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clickable {
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Tourist Dashboard",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        TravelGuide(
            bgImage = R.drawable.travel_image,
            title = "Destination Search",
            onClick = {
                context.startActivity(Intent(context, DestinationSearchActivity::class.java))
            }
        )
        TravelGuide(
            bgImage = R.drawable.accommodation_image,
            title = "Accommodation Search",
            onClick = {

            }
        )
        TravelGuide(
            bgImage = R.drawable.transportation_image,
            title = "Transportation Search",
            onClick = {

            }
        )
        TravelGuide(
            bgImage = R.drawable.reviews_image,
            title = "Reviews",
            onClick = {

            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

