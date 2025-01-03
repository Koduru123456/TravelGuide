package hemanth.S3083018.travelguide.ui.theme

import android.app.Activity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import hemanth.S3083018.travelguide.R

class PlaceDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewDetailsScreen()
        }
    }
}

@Composable
fun ViewDetailsScreen() {

    val selectedPlace = SelectedPlace.placeDetails

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.SkyBlue))
                .padding(vertical = 8.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clickable {
                        context.finish()
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Places Details",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = selectedPlace.placeImage),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = selectedPlace.placeName,
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 6.dp)

        )

        Text(
            text = selectedPlace.placeLocation,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 2.dp)
        )

        Text(
            text = "Timings",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        )

        Text(
            text = selectedPlace.openingHours,
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
        )

        Text(
            text = "Contact Information",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.width(12.dp))
            Image(
                modifier = Modifier
                    .size(22.dp),
                painter = painterResource(id = R.drawable.ic_call),
                contentDescription = "call image"

            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = selectedPlace.contactNumberOne,
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier

            )

        }

        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.width(12.dp))
            Image(
                modifier = Modifier
                    .size(22.dp),
                painter = painterResource(id = R.drawable.ic_call),
                contentDescription = "call image"

            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = selectedPlace.contactNumberTwo,
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier

            )

        }

        Text(
            text = "Attractions",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp)
        )

        Text(
            text = selectedPlace.attractions,
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
        )

        Text(
            text = "Activities",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp)
        )

        Text(
            text = selectedPlace.activities,
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
        )

        Text(
            text = "Description",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp)
        )

        Text(
            text = selectedPlace.placeDescription,
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp)
        )


    }

}


@Preview(showBackground = true)
@Composable
fun ViewDetailsScreenPreview() {

    ViewDetailsScreen()
}
