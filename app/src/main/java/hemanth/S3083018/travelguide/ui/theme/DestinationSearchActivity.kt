package hemanth.S3083018.travelguide.ui.theme

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hemanth.S3083018.travelguide.R

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

@Composable
fun DestinationSearchScreen(
    onPlaceDetails: (placeDetails: PlaceDetails) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current as Activity

    var selectedPlaceCategory by remember { mutableStateOf("All") }

    val places = if (selectedPlaceCategory == "All") {
        getPlaceDetails()
    } else {
        getPlaceDetails().filter { it.category == selectedPlaceCategory }
    }

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
            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        context.finish()
                    }
                    .padding(start = 4.dp)

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
            selPlace = selectedPlaceCategory,
            onPlaceSelected = { selectedPlaceCategory = it }
        )

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
            category = "Historical Landmarks",
            placeImage = R.drawable.tees_transporter_bridge,
            placeLocation = "Middlesbrough, England",
            openingHours = "Visitor Centre: Mon-Fri, 9:00 AM - 4:00 PM",
            attractions = "360-degree camera views, Heritage exhibitions, Gondola tours (currently suspended)",
            activities = "Guided tours, Photography, Exploring industrial heritage",
            contactNumberOne = "+44 1642 727265",
            contactNumberTwo = "+44 1642 727266",
            placeDescription = "The Tees Transporter Bridge, opened in 1911, is an iconic engineering marvel and a symbol of Middlesbrough's industrial heritage. While the bridge itself is temporarily closed for structural assessments, visitors can explore the Visitor Centre to learn about its history and enjoy unique panoramic views through advanced digital cameras."
        ),
        PlaceDetails(
            placeName = "Roseberry Topping",
            category = "Geologic Formations",
            placeImage = R.drawable.roseberry_topping,
            placeLocation = "North Yorkshire, England",
            openingHours = "Open 24 hours",
            attractions = "Panoramic views, Hiking trails, Wildlife spotting",
            activities = "Hiking, Photography, Nature exploration",
            contactNumberOne = "+44 345 130 6229",
            contactNumberTwo = "+44 345 130 6229",
            placeDescription = "Roseberry Topping is a striking hill in North Yorkshire, England, known for its unique shape and sweeping views of the surrounding countryside. It offers a popular hiking route and is managed by the National Trust, making it a favorite destination for nature enthusiasts and photographers."
        ),
        PlaceDetails(
            placeName = "Riverside Stadium",
            category = "Arenas & Stadiums",
            placeImage = R.drawable.riverside_stadium,
            placeLocation = "Middlesbrough, England",
            openingHours = "Matchdays: 10:00 AM - 5:00 PM; Non-matchdays: Closed to the public",
            attractions = "Football matches, Stadium tours, Middlesbrough FC museum",
            activities = "Watching matches, Guided tours, Merchandise shopping",
            contactNumberOne = "+44 1642 757640",
            contactNumberTwo = "+44 1642 757648",
            placeDescription = "Riverside Stadium is the home of Middlesbrough Football Club, located on the banks of the River Tees. It offers a fantastic venue for football fans, with guided tours available on non-matchdays, giving visitors an insight into the club's history and facilities."
        ),
        PlaceDetails(
            placeName = "Ormesby Hall",
            category = "Historic Sites",
            placeImage = R.drawable.ormesby_hall,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:30 AM - 4:30 PM (Wednesday to Sunday)",
            attractions = "Historic Georgian Mansion, Gardens, Model Railways",
            activities = "Heritage Tours, Family Events, Garden Walks",
            contactNumberOne = "+44 1642 328904",
            contactNumberTwo = "+44 1642 328904",
            placeDescription = "Ormesby Hall is a Georgian mansion set in picturesque grounds in Middlesbrough, England. Managed by the National Trust, it offers a glimpse into the life of a prominent local family, along with model railway exhibitions and serene gardens for visitors to enjoy."
        ),
        PlaceDetails(
            placeName = "Newham Grange Farm",
            category = "Farms",
            placeImage = R.drawable.newham_grange_farm,
            placeLocation = "Middlesbrough, England",
            openingHours = "9:30 AM - 4:30 PM (Tuesday to Sunday)",
            attractions = "Rare Breed Animals, Play Area, Farm Shop",
            activities = "Animal Feeding, Children’s Activities, Nature Walks",
            contactNumberOne = "+44 1642 515729",
            contactNumberTwo = "+44 1642 515729",
            placeDescription = "Newham Grange Farm is a family-friendly farm located in Middlesbrough, England. It is home to rare breed animals, a children’s play area, and a farm shop offering local produce. A perfect destination for a fun day out with family."
        ),
        PlaceDetails(
            placeName = "St Mary's Cathedral",
            category = "Churches & Cathedrals",
            placeImage = R.drawable.st_mary_cathedral,
            placeLocation = "Sydney, Australia",
            openingHours = "6:30 AM - 6:00 PM",
            attractions = "Gothic Architecture, Historic Organ, Peaceful Gardens",
            activities = "Religious Services, Guided Tours, Photography",
            contactNumberOne = "+61 2 9220 0400",
            contactNumberTwo = "+61 2 9220 0400",
            placeDescription = "St Mary’s Cathedral in Sydney is a magnificent example of English Gothic architecture. As the spiritual home of Sydney’s Catholic community, it offers a serene space for prayer, historic tours, and a stunning backdrop for photography."
        ),
        PlaceDetails(
            placeName = "Lordstones Country Park",
            category = "Hiking Trails",
            placeImage = R.drawable.lord_stone_country_park,
            placeLocation = "North York Moors, England",
            openingHours = "8:30 AM - 5:00 PM",
            attractions = "Stunning Landscapes, Walking Trails, Picnic Spots",
            activities = "Hiking, Bird Watching, Camping",
            contactNumberOne = "+44 1642 778482",
            contactNumberTwo = "+44 1642 778482",
            placeDescription = "Lordstones Country Park is a hidden gem nestled in the North York Moors, offering breathtaking walking trails, serene picnic spots, and panoramic views of the countryside. It's a perfect destination for nature enthusiasts and adventure seekers."
        ),
        PlaceDetails(
            placeName = "MIMA, Middlesbrough Institute of Modern Art",
            category = "Art Museums",
            placeImage = R.drawable.mima_middlesbrough_institute,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:00 AM - 4:30 PM (Tuesday to Saturday)",
            attractions = "Contemporary Art, Sculpture Gallery, Creative Workshops",
            activities = "Art Viewing, Community Events, Educational Programs",
            contactNumberOne = "+44 1642 931232",
            contactNumberTwo = "+44 1642 931232",
            placeDescription = "MIMA, located in Middlesbrough, England, is one of the UK's leading contemporary art galleries. It showcases stunning exhibitions, sculptures, and creative workshops, making it a cultural hub for art enthusiasts."
        ),
        PlaceDetails(
            placeName = "Orange Pip Market",
            category = "Farmers Markets",
            placeImage = R.drawable.orange_pip_market,
            placeLocation = "Middlesbrough, England",
            openingHours = "12:00 PM - 7:00 PM (Last Saturday of the Month)",
            attractions = "Street Food Stalls, Live Music, Artisan Goods",
            activities = "Shopping, Food Tasting, Socializing",
            contactNumberOne = "+44 1642 729135",
            contactNumberTwo = "+44 1642 729135",
            placeDescription = "Orange Pip Market is a vibrant street market held monthly in Middlesbrough, England. Known for its diverse food stalls, artisan products, and live entertainment, it’s a must-visit for foodies and culture lovers alike."
        ),
        PlaceDetails(
            placeName = "Jamia Mosque Almadina",
            category = "Religious Sites",
            placeImage = R.drawable.jamia_mosque_al_madinah,
            placeLocation = "Middlesbrough, England",
            openingHours = "5:00 AM - 9:00 PM",
            attractions = "Beautiful Islamic Architecture, Peaceful Ambiance",
            activities = "Prayers, Community Gatherings, Religious Events",
            contactNumberOne = "+44 1642 123456",
            contactNumberTwo = "+44 1642 654321",
            placeDescription = "Jamia Mosque Almadina is a prominent mosque in Middlesbrough, known for its stunning Islamic architecture and serene environment. It serves as a center for worship, education, and community events for the local Muslim population."
        ),
        PlaceDetails(
            placeName = "Cineworld Cinemas",
            category = "Theatres",
            placeImage = R.drawable.cine_world_cinema,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:00 AM - 11:00 PM",
            attractions = "Latest Blockbusters, IMAX Experience, Comfortable Seating",
            activities = "Movie Watching, Dining, Family Entertainment",
            contactNumberOne = "+44 871 200 2000",
            contactNumberTwo = "+44 871 220 1234",
            placeDescription = "Cineworld Cinemas in Middlesbrough is a popular spot for movie enthusiasts, offering state-of-the-art screens, including IMAX, and a wide selection of films. It’s an ideal destination for entertainment with friends and family."
        ),
        PlaceDetails(
            placeName = "Middlesbrough Theatre",
            category = "Theatres",
            placeImage = R.drawable.middlesbrough_theatre,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:00 AM - 6:00 PM (Box Office)",
            attractions = "Live Performances, Drama Shows, Community Events",
            activities = "Theatre Shows, Live Concerts, Family Events",
            contactNumberOne = "+44 1642 815181",
            contactNumberTwo = "+44 1642 815182",
            placeDescription = "Middlesbrough Theatre is a vibrant cultural venue hosting a variety of performances, including dramas, musicals, and live concerts. With its rich history and welcoming atmosphere, it’s a hub for performing arts in the region."
        ),
        PlaceDetails(
            placeName = "RSPB Saltholme",
            category = "Nature & Wildlife Areas",
            placeImage = R.drawable.rspb_saltholme,
            placeLocation = "Stockton-on-Tees, England",
            openingHours = "9:30 AM - 4:00 PM (Closed on Christmas Day)",
            attractions = "Birdwatching, Wetlands, Visitor Centre",
            activities = "Nature Walks, Wildlife Photography, Educational Activities",
            contactNumberOne = "+44 1642 546625",
            contactNumberTwo = "+44 1642 546626",
            placeDescription = "RSPB Saltholme is a stunning nature reserve located in Stockton-on-Tees, featuring wetlands teeming with bird species. It offers beautiful walking trails, a visitor center, and family-friendly activities to explore"
        ),
        PlaceDetails(
            placeName = "Seal Sands Nature Reserve",
            category = "Nature & Wildlife Areas",
            placeImage = R.drawable.seal_sands_nature_reserve,
            placeLocation = "Teesside, England",
            openingHours = "Open 24 hours (Accessible during daylight)",
            attractions = "Seals, Mudflats, Rare Wildlife",
            activities = "Wildlife Spotting, Nature Walks, Photography",
            contactNumberOne = "+44 1642 123456",
            contactNumberTwo = "+44 1642 654321",
            placeDescription = "Seal Sands Nature Reserve, part of the Teesmouth National Nature Reserve, is a unique habitat for seals and a variety of bird species. It's a must-visit for nature enthusiasts looking to experience Teesside's rich biodiversity."
        ),
        PlaceDetails(
            placeName = "Cleveland Centre",
            category = "Shopping Malls",
            placeImage = R.drawable.cleveland_centre,
            placeLocation = "Middlesbrough, England",
            openingHours = "9:00 AM - 5:30 PM (Varies by store)",
            attractions = "High-Street Stores, Cafes, Shopping Experience",
            activities = "Shopping, Dining, Leisure Activities",
            contactNumberOne = "+44 1642 211123",
            contactNumberTwo = "+44 1642 211124",
            placeDescription = "The Cleveland Centre in Middlesbrough is a vibrant shopping destination featuring a mix of high-street stores, boutique shops, and cozy cafes. It's a hub for locals and visitors looking for a convenient and enjoyable retail experience."
        ),
        PlaceDetails(
            placeName = "Hillstreet Shopping Centre",
            category = "Shopping Malls",
            placeImage = R.drawable.hill_street_shopping_centre,
            placeLocation = "Middlesbrough, England",
            openingHours = "9:00 AM - 5:30 PM (Closed on Christmas Day)",
            attractions = "Retail Stores, Cafes, Dining Options",
            activities = "Shopping, Dining, Leisure Activities",
            contactNumberOne = "+44 1642 218163",
            contactNumberTwo = "+44 1642 218164",
            placeDescription = "Hillstreet Shopping Centre in Middlesbrough offers a variety of shops, cafes, and restaurants. It's a popular spot for both locals and tourists looking for a diverse shopping experience."
        ),
        PlaceDetails(
            placeName = "Dundas Shopping Centre",
            category = "Shopping Malls",
            placeImage = R.drawable.dundas_shopping_centre,
            placeLocation = "Middlesbrough, England",
            openingHours = "9:00 AM - 5:30 PM (Varies by store)",
            attractions = "High Street Shops, Cafes, Retail Experiences",
            activities = "Shopping, Dining, Socializing",
            contactNumberOne = "+44 1642 240000",
            contactNumberTwo = "+44 1642 240001",
            placeDescription = "The Dundas Shopping Centre in Middlesbrough features a variety of shops, offering everything from fashion to technology. It's a well-loved shopping destination in the heart of the town."
        ),
        PlaceDetails(
            placeName = "Fairy Dell",
            category = "Parks",
            placeImage = R.drawable.fairy_dell,
            placeLocation = "Middlesbrough, England",
            openingHours = "Open 24 hours (Accessible during daylight)",
            attractions = "Scenic Trails, Nature Walks, Waterfalls",
            activities = "Nature Walks, Photography, Picnics",
            contactNumberOne = "+44 1642 774774",
            contactNumberTwo = "+44 1642 774775",
            placeDescription = "Fairy Dell is a tranquil nature reserve located in Middlesbrough. With scenic walking trails, waterfalls, and lush greenery, it's a peaceful spot for nature lovers and outdoor enthusiasts."
        ),
        PlaceDetails(
            placeName = "Stewart Park",
            category = "Parks",
            placeImage = R.drawable.stewart_park,
            placeLocation = "Middlesbrough, England",
            openingHours = "7:30 AM - Dusk",
            attractions = "Lake, Historic Gardens, Children’s Playground",
            activities = "Picnics, Nature Walks, Playgrounds, Wildlife Watching",
            contactNumberOne = "+44 1642 511243",
            contactNumberTwo = "+44 1642 511244",
            placeDescription = "Stewart Park in Middlesbrough is a beautiful green space with a historic garden, a lake, and a children’s playground. It offers an ideal setting for outdoor activities, picnics, and wildlife watching."
        ),
        PlaceDetails(
            placeName = "Albert Park",
            category = "Parks",
            placeImage = R.drawable.albert_park,
            placeLocation = "Middlesbrough, England",
            openingHours = "7:00 AM - Dusk",
            attractions = "Boating Lake, Formal Gardens, Historic Features",
            activities = "Boating, Walking, Jogging, Photography",
            contactNumberOne = "+44 1642 248428",
            contactNumberTwo = "+44 1642 248429",
            placeDescription = "Albert Park in Middlesbrough is a Victorian-era park featuring formal gardens, a boating lake, and a variety of historic features. It's a popular spot for outdoor activities like jogging, walking, and photography."
        ),
        PlaceDetails(
            placeName = "Flatts Lane Woodland Country Park",
            category = "Parks",
            placeImage = R.drawable.flatts_lane_woodland_country_park,
            placeLocation = "Middlesbrough, England",
            openingHours = "Open 24 Hours (Daylight Hours Recommended)",
            attractions = "Woodland Trails, Wildlife, Scenic Views",
            activities = "Hiking, Nature Walks, Birdwatching",
            contactNumberOne = "+44 1642 516780",
            contactNumberTwo = "+44 1642 516781",
            placeDescription = "Flatts Lane Woodland Country Park in Middlesbrough is a beautiful natural reserve with scenic walking trails, woodlands, and a wide range of wildlife. It's perfect for hiking, birdwatching, and enjoying the outdoors."
        ),
        PlaceDetails(
            placeName = "Teessaurus Park",
            category = "Parks",
            placeImage = R.drawable.teessaurus_park,
            placeLocation = "Middlesbrough, England",
            openingHours = "7:30 AM - 6:00 PM",
            attractions = "Dinosaur Sculptures, Scenic Views, Wildlife",
            activities = "Walking, Sightseeing, Photography",
            contactNumberOne = "+44 1642 813 062",
            contactNumberTwo = "+44 1642 813 061",
            placeDescription = "Teessaurus Park is a fun and unique park in Middlesbrough, featuring large dinosaur sculptures, a nature reserve, and walking paths. It's a great location for sightseeing, photography, and wildlife spotting."
        ),
        PlaceDetails(
            placeName = "Centre Square Park",
            category = "Parks",
            placeImage = R.drawable.center_squar_park,
            placeLocation = "Middlesbrough, England",
            openingHours = "Open 24 Hours (Daylight Hours Recommended)",
            attractions = "Cultural Events, Water Features, Public Art",
            activities = "Picnics, Relaxation, Sightseeing, Cultural Events",
            contactNumberOne = "+44 1642 228202",
            contactNumberTwo = "+44 1642 228200",
            placeDescription = "Centre Square Park in the heart of Middlesbrough is a cultural hub with public art, water features, and space for outdoor events. It’s perfect for a day of relaxation, sightseeing, or enjoying local performances."
        ),
        PlaceDetails(
            placeName = "Hatchet Harry's Axe Throwing Middlesbrough",
            category = "Game & Entertainment Centres",
            placeImage = R.drawable.hatchet_harrys_axe_throwing_middlesbrough,
            placeLocation = "Middlesbrough, England",
            openingHours = "12:00 PM - 10:00 PM",
            attractions = "Axe Throwing, Group Events, Competitive Games",
            activities = "Axe Throwing, Social Events, Team Building",
            contactNumberOne = "+44 1642 463 332",
            contactNumberTwo = "+44 1642 463 333",
            placeDescription = "Hatchet Harry's Axe Throwing in Middlesbrough is a thrilling entertainment centre where you can try your hand at axe throwing, perfect for group events, team-building activities, and friendly competition."
        ),
        PlaceDetails(
            placeName = "Teesside Karting",
            category = "Game & Entertainment Centres",
            placeImage = R.drawable.teesside_karting,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:00 AM - 8:00 PM",
            attractions = "Indoor Karting, Outdoor Karting, Events",
            activities = "Go-Kart Racing, Events, Corporate Events, Family Activities",
            contactNumberOne = "+44 1642 231 404",
            contactNumberTwo = "+44 1642 233 123",
            placeDescription = "Teesside Karting is a popular go-karting centre in Middlesbrough, offering indoor and outdoor karting tracks. It's perfect for thrill-seekers, families, and corporate events. They also host racing competitions and fun days out."
        ),
        PlaceDetails(
            placeName = "Rock Antics LTD",
            category = "Game & Entertainment Centres",
            placeImage = R.drawable.rock_antics_ltd,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:00 AM - 11:00 PM",
            attractions = "Indoor Climbing, Rock Walls, Bouldering",
            activities = "Climbing, Team Building, Fitness Training",
            contactNumberOne = "+44 1642 242 555",
            contactNumberTwo = "+44 1642 242 556",
            placeDescription = "Rock Antics is an indoor rock climbing centre in Middlesbrough, offering bouldering and wall climbing experiences. It is a great place for fitness enthusiasts and those looking for a fun way to stay active. They also offer classes and team-building activities."
        ),
        PlaceDetails(
            placeName = "Henry Bolckow Statue",
            category = "Monuments & Statues",
            placeImage = R.drawable.henry_bolckow_statue,
            placeLocation = "Middlesbrough, England",
            openingHours = "Open 24 Hours",
            attractions = "Historical Statue, Public Art, Landmark",
            activities = "Sightseeing, Photography",
            contactNumberOne = "+44 1642 729 002",
            contactNumberTwo = "+44 1642 724 052",
            placeDescription = "The Henry Bolckow Statue in Middlesbrough commemorates one of the founders of the town’s steel industry. It's a significant landmark and historical piece of public art, attracting visitors interested in the town's industrial history."
        ),
        PlaceDetails(
            placeName = "Sir Samuel Alexander Sadler Statue",
            category = "Monuments & Statues",
            placeImage = R.drawable.sir_samuel_alexander_sadler_statue,
            placeLocation = "Middlesbrough, England",
            openingHours = "Open 24 Hours",
            attractions = "Historical Statue, Public Art, Landmark",
            activities = "Sightseeing, Photography",
            contactNumberOne = "+44 1642 729 002",
            contactNumberTwo = "+44 1642 724 052",
            placeDescription = "The Sir Samuel Alexander Sadler Statue is a historical monument located in Middlesbrough, dedicated to Sir Samuel Sadler, a key figure in the town’s industrial development. It’s a significant piece of public art and a popular landmark for visitors and history enthusiasts."
        ),
        PlaceDetails(
            placeName = "Middlesbrough Municipal Golf Centre",
            category = "Golf Courses",
            placeImage = R.drawable.middlesbrough_municipal_golf_centre,
            placeLocation = "Middlesbrough, England",
            openingHours = "7:00 AM - 9:00 PM",
            attractions = "18-Hole Golf Course, Driving Range, Cafe",
            activities = "Golfing, Driving Range, Golf Lessons",
            contactNumberOne = "+44 1642 250 287",
            contactNumberTwo = "+44 1642 250 288",
            placeDescription = "Middlesbrough Municipal Golf Centre is an 18-hole golf course offering a great experience for golf enthusiasts of all levels. The centre also features a driving range and a cafe for visitors. It’s ideal for a relaxing day of golf or improving your skills."
        ),
        PlaceDetails(
            placeName = "Wilton Golf Club",
            category = "Golf Courses",
            placeImage = R.drawable.wilton_golf_club,
            placeLocation = "Redcar, England",
            openingHours = "7:00 AM - 8:00 PM",
            attractions = "18-Hole Golf Course, Historic Clubhouse",
            activities = "Golfing, Golf Lessons, Events",
            contactNumberOne = "+44 1642 458 875",
            contactNumberTwo = "+44 1642 459 998",
            placeDescription = "Wilton Golf Club, located in Redcar, England, offers a beautiful 18-hole golf course with a historic clubhouse. It’s a popular destination for golfers and hosts regular events and tournaments. Whether you’re a beginner or an experienced player, it provides a great environment to enjoy the sport."
        ),
        PlaceDetails(
            placeName = "Sapphires Middlesbrough",
            category = "Bars & Clubs",
            placeImage = R.drawable.sapphires_middlesbrough,
            placeLocation = "Middlesbrough, England",
            openingHours = "9:00 PM - 4:00 AM",
            attractions = "Nightlife, Dance Floors, VIP Rooms",
            activities = "Dancing, Nightlife, Live DJs",
            contactNumberOne = "+44 1642 249 777",
            contactNumberTwo = "+44 1642 249 788",
            placeDescription = "Sapphires Middlesbrough is a popular nightclub known for its vibrant nightlife and electric atmosphere. It offers live DJ performances, dance floors, and VIP rooms for an unforgettable night out."
        ),
        PlaceDetails(
            placeName = "Flares & Popworld",
            category = "Bars & Clubs",
            placeImage = R.drawable.flares_and_popworld,
            placeLocation = "Middlesbrough, England",
            openingHours = "10:00 PM - 3:00 AM",
            attractions = "Retro Vibes, Dance Floors, Theme Nights",
            activities = "Dancing, Karaoke, Theme Nights",
            contactNumberOne = "+44 1642 213 133",
            contactNumberTwo = "+44 1642 213 134",
            placeDescription = "Flares & Popworld is a lively bar and club offering retro vibes, dance floors, and themed nights. It’s known for its lively karaoke sessions, music from the past decades, and a great place for dancing and partying in Middlesbrough."
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceCategoryDropDown(
    placecategories: List<String>,
    selPlace: String,
    onPlaceSelected: (String) -> Unit
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
            value = selPlace,
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
                        onPlaceSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DestinationSearchScreenPreview() {
    DestinationSearchScreen(onPlaceDetails = {})
}
