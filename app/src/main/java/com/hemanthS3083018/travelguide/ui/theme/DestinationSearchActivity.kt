package com.hemanthS3083018.travelguide.ui.theme

import android.app.Activity
import android.content.Intent
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hemanthS3083018.travelguide.R

class DestinationSearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationSearchScreen(::onPlaceSelected)
        }
    }

    private fun onPlaceSelected(placeDetails: PlaceDetails) {
        SelectedPlace.placeDetails = placeDetails
        startActivity(Intent(this, PlaceDetailsActivity::class.java))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationSearchScreen(
    onPlaceDetails: (placeDetails: PlaceDetails) -> Unit
) {
    // State to hold search query
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current as Activity

    // Filtered list based on search query
//    val places = getPlaceDetails().filter {
//        it.placeName.contains(searchQuery, ignoreCase = true)
//    }

    var selectedPlaceCategory by remember { mutableStateOf("All") }

    val places = if (selectedPlaceCategory == "All") {
        getPlaceDetails()
    } else {
        getPlaceDetails().filter { it.category == selectedPlaceCategory }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.SkyBlue))
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        context.finish()
                    }
                    .padding(start = 4.dp) // Optional spacing // Optional spacin

            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_travelguide),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clickable { }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Places to visit",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )


        }

        /*
        // Search bar at the top
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text(text = "Search places...")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search Icon"
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )
        */
        
        Spacer(modifier = Modifier.height(12.dp))

        PlaceCategoryDropDown(
            placecategories = listOf(
                "All",
                "Bridges",
                "Geologic Formations",
                "Arenas & Stadiums",
                "Historic Sites",
                "Farms",
                "Churches & Cathedrals",
                "Hiking Trails",
                "Art Museums",
                "Farmers Markets",
                "Religious Sites",
                "Theatres",
                "Nature & Wildlife Areas",
                "Shopping Malls",
                "Parks",
                "Game & Entertainment Centres",
                "Monuments & Statues",
                "Golf Courses",
                "Bars & Clubs",
            ),
            selectedPlaceCategory = selectedPlaceCategory,
            onPlaceCategorySelected = { selectedPlaceCategory = it }
        )

        // List of places
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
                modifier = Modifier
                    .padding(start = 12.dp)

            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = placeDetails.placeLocation
                )

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


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "Opening Hours Icon"
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


                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                )

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
            placeName = "Tees Transporter Bridge",
            category = "Bridges",
            placeImage = R.drawable.tees_transporter_bridge, // Replace with your image resource
            placeLocation = "New York, NY",
            openingHours = "6:00 AM - 1:00 AM",
            attractions = "Boating, Scenic Walks, Zoo",
            activities = "Jogging, Picnics, Cycling",
            contactNumberOne = "+1 212-310-6600",
            contactNumberTwo = "+1 212-310-6601",
            placeDescription = "Central Park is a sprawling urban park in New York City, offering lush green spaces, scenic walking paths, and a variety of recreational activities. It is a serene escape from the bustling city life."
        ),
        PlaceDetails(
            placeName = "Roseberry Topping",
            category = "Geologic Formations",
            placeImage = R.drawable.roseberry_topping, // Replace with your image resource
            placeLocation = "Paris, France",
            openingHours = "9:00 AM - 6:00 PM",
            attractions = "Mona Lisa, Ancient Artifacts",
            activities = "Art Viewing, Guided Tours",
            contactNumberOne = "+33 1 40 20 50 50",
            contactNumberTwo = "+33 1 40 20 50 51",
            placeDescription = "The Louvre Museum in Paris is the world's largest art museum and a historic monument. It houses an impressive collection of art, including the famous Mona Lisa and various ancient artifacts."
        ),
        PlaceDetails(
            placeName = "Riverside Stadium",
            category = "Arenas & Stadiums",
            placeImage = R.drawable.riverside_stadium, // Replace with your image resource
            placeLocation = "Sydney, Australia",
            openingHours = "9:00 AM - 5:00 PM",
            attractions = "Architectural Beauty, Opera Events",
            activities = "Performances, Dining, Tours",
            contactNumberOne = "+61 2 9250 7111",
            contactNumberTwo = "+61 2 9250 7222",
            placeDescription = "The Sydney Opera House is an iconic architectural marvel in Australia, known for its unique design and world-class performances, offering an unforgettable cultural experience."
        ),
        PlaceDetails(
            placeName = "Ormesby Hall",
            category = "Historic Sites",
            placeImage = R.drawable.ormesby_hall, // Replace with your image resource
            placeLocation = "Beijing, China",
            openingHours = "7:00 AM - 6:00 PM",
            attractions = "Historical Sites, Watchtowers",
            activities = "Hiking, Sightseeing",
            contactNumberOne = "+86 10 6162 6505",
            contactNumberTwo = "+86 10 6162 6506",
            placeDescription = "The Great Wall of China is a breathtaking ancient structure spanning thousands of miles. It offers stunning views, rich history, and a testament to ancient engineering marvels."
        ),
        PlaceDetails(
            placeName = "Newham Grange Farm",
            category = "Farms",
            placeImage = R.drawable.newham_grange_farm, // Replace with your image resource
            placeLocation = "Agra, India",
            openingHours = "6:00 AM - 7:00 PM",
            attractions = "Marble Tomb, Mughal Architecture",
            activities = "Photography, Sightseeing",
            contactNumberOne = "+91 562 222 7261",
            contactNumberTwo = "+91 562 222 7262",
            placeDescription = "The Taj Mahal, located in Agra, India, is a UNESCO World Heritage Site. It is a stunning marble mausoleum and a symbol of eternal love, attracting millions of visitors annually."
        ),
        PlaceDetails(
            placeName = "St Mary's Cathedral",
            category = "Churches & Cathedrals",
            placeImage = R.drawable.st_mary_cathedral, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Lordstones Country Park",
            category = "Hiking Trails",
            placeImage = R.drawable.lord_stone_country_park, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "MIMA, Middlesbrough Institute of Modern Art",
            category = "Art Museums",
            placeImage = R.drawable.mima_middlesbrough_institute, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Orange Pip Market",
            category = "Farmers Markets",
            placeImage = R.drawable.orange_pip_market, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Jamia Mosque Almadina",
            category = "Religious Sites",
            placeImage = R.drawable.jamia_mosque_al_madinah, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Cineworld Cinemas",
            category = "Theatres",
            placeImage = R.drawable.cine_world_cinema, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Middlesbrough Theatre",
            category = "Theatres",
            placeImage = R.drawable.middlesbrough_theatre, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "RSPB Saltholme",
            category = "Nature & Wildlife Areas",
            placeImage = R.drawable.rspb_saltholme, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Seal Sands Nature Reserve",
            category = "Nature & Wildlife Areas",
            placeImage = R.drawable.seal_sands_nature_reserve, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Cleveland Centre",
            category = "Shopping Malls",
            placeImage = R.drawable.cleveland_centre, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Hillstreet Shopping Centre",
            category = "Shopping Malls",
            placeImage = R.drawable.hill_street_shopping_centre, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Dundas Shopping Centre",
            category = "Shopping Malls",
            placeImage = R.drawable.dundas_shopping_centre, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Fairy Dell",
            category = "Parks",
            placeImage = R.drawable.fairy_dell, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Stewart Park",
            category = "Parks",
            placeImage = R.drawable.stewart_park, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Albert Park",
            category = "Parks",
            placeImage = R.drawable.albert_park, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Flatts Lane Woodland Country Park",
            category = "Parks",
            placeImage = R.drawable.flatts_lane_woodland_country_park, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Teessaurus Park",
            category = "Parks",
            placeImage = R.drawable.teessaurus_park, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Centre Square Park",
            category = "Parks",
            placeImage = R.drawable.center_squar_park, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Hatchet Harry's Axe Throwing Middlesbrough",
            category = "Game & Entertainment Centres",
            placeImage = R.drawable.hatchet_harrys_axe_throwing_middlesbrough, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Teesside Karting",
            category = "Game & Entertainment Centres",
            placeImage = R.drawable.teesside_karting, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Rock Antics LTD",
            category = "Game & Entertainment Centres",
            placeImage = R.drawable.rock_antics_ltd, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Henry Bolckow Statue",
            category = "Monuments & Statues",
            placeImage = R.drawable.henry_bolckow_statue, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Sir Samuel Alexander Sadler Statue",
            category = "Monuments & Statues",
            placeImage = R.drawable.sir_samuel_alexander_sadler_statue, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Middlesbrough Municipal Golf Centre",
            category = "Golf Courses",
            placeImage = R.drawable.middlesbrough_municipal_golf_centre, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Wilton Golf Club",
            category = "Golf Courses",
            placeImage = R.drawable.wilton_golf_club, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Sapphires Middlesbrough",
            category = "Bars & Clubs",
            placeImage = R.drawable.sapphires_middlesbrough, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        ),
        PlaceDetails(
            placeName = "Flares & Popworld",
            category = "Bars & Clubs",
            placeImage = R.drawable.flares_and_popworld, // Replace with your image resource
            placeLocation = "Rio de Janeiro, Brazil",
            openingHours = "8:00 AM - 6:00 PM",
            attractions = "Panoramic Views, Iconic Statue",
            activities = "Sightseeing, Cable Car Rides",
            contactNumberOne = "+55 21 2558 1329",
            contactNumberTwo = "+55 21 2558 1330",
            placeDescription = "Christ the Redeemer is a magnificent statue in Rio de Janeiro, Brazil. It offers breathtaking panoramic views of the city and serves as a symbol of peace and hospitality."
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceCategoryDropDown(
    placecategories: List<String>,
    selectedPlaceCategory: String,
    onPlaceCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        TextField(
            value = selectedPlaceCategory,
            onValueChange = {},
            readOnly = true,
            label = { Text("Filter by Categories") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()

        ) {
            placecategories.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onPlaceCategorySelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
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
