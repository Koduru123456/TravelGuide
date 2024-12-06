package com.hemanth.travelguide.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemanth.travelguide.R

class DestinationSearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationSearchScreen(onPlaceDetails = {})
        }
    }
}

@Composable
fun DestinationSearchScreen(
    onPlaceDetails: (placeDetails: PlaceDetails) -> Unit
) {
    val places = getPlaceDetails()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

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
                text = "Places to visit",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {

            items(places.size) { index ->

                PlaceCard(places[index], onPlaceDetails)
            }
        }
    }

}


@Composable
fun PlaceCard(
    placeDetails: PlaceDetails,
    onPlaceDetails: (placeDetails: PlaceDetails) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onPlaceDetails.invoke(placeDetails) },
        elevation = CardDefaults.cardElevation(4.dp),
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = placeDetails.placeImage),

                contentDescription = "Place Image",
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = placeDetails.placeName,

                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 12.dp)

            )
            Spacer(modifier = Modifier.height(4.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = placeDetails.placeLocation)

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = placeDetails.placeLocation,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                )
            }

            Spacer(modifier = Modifier.height(4.dp))


            Row (
                verticalAlignment = Alignment.CenterVertically
            ){

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "Opening Hours Icon",

                    )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = placeDetails.openingHours,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row {


                Spacer(modifier = Modifier
                    .weight(1f)
                    .height(2.dp))

                Text(
                    text = "View More Details",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.width(6.dp))

            }

            Spacer(modifier = Modifier.height(6.dp))



        }
    }
}

fun getPlaceDetails(): List<PlaceDetails> {
    return listOf(
        PlaceDetails(
            placeName = "Central Park",
            placeImage = R.drawable.central_park, // Replace with your image resource
            placeLocation = "New York, NY",
            openingHours = "6:00 AM - 1:00 AM"
        ),
        PlaceDetails(
            placeName = "Louvre Museum",
            placeImage = R.drawable.louvre_museum, // Replace with your image resource
            placeLocation = "Paris, France",
            openingHours = "9:00 AM - 6:00 PM"
        ),
        PlaceDetails(
            placeName = "Sydney Opera House",
            placeImage = R.drawable.sydney_opera_house, // Replace with your image resource
            placeLocation = "Sydney, Australia",
            openingHours = "9:00 AM - 5:00 PM"
        ),
        PlaceDetails(
            placeName = "Great Wall of China",
            placeImage = R.drawable.great_wall_of_china, // Replace with your image resource
            placeLocation = "Beijing, China",
            openingHours = "7:00 AM - 6:00 PM"
        ),
        PlaceDetails(
            placeName = "Taj Mahal",
            placeImage = R.drawable.taj_mahal, // Replace with your image resource
            placeLocation = "Agra, India",
            openingHours = "6:00 AM - 7:00 PM"
        ),
        PlaceDetails(
            placeName = "Christ the Redeemer",
            placeImage = R.drawable.christ_redeemer, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM"
        )
    )
}


@Composable
fun PlacesCard(
    placeDetails: PlaceDetails,
    onPlaceDetails: (placeDetails: PlaceDetails) -> Unit

) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onPlaceDetails.invoke(placeDetails) },
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = placeDetails.placeImage),
                contentDescription = placeDetails.placeName,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = placeDetails.placeName, style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = placeDetails.placeLocation, style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DemoScreenPreview() {
    DestinationSearchScreen(onPlaceDetails = {})
}
