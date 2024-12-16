package com.hemanthS3083018.travelguide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hemanthS3083018.travelguide.ui.theme.TravelGuideTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelGuideTheme {
                LoadingScreenCheck(::isUserLoggedIn)
            }
        }
    }

    private fun isUserLoggedIn(value: Int) {

        when (value) {
            2 -> {
                gotoSignInActivity(this)
            }

        }
    }
}

@Composable
fun LoadingScreenCheck(isUserLoggedIn: (value: Int) -> Unit) {
    var showSplash by remember { mutableStateOf(true) }
    val context = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        delay(3000)
        showSplash = false
    }

    if (showSplash) {
        LoadingScreen()

    } else {
        val TravelGuideStatus = TravelGuideDetails.getTravelguideuserStatus(context)

        if (TravelGuideStatus) {
            context.startActivity(Intent(context, HomeActivity::class.java))
            context.finish()
        } else {
            context.startActivity(Intent(context, SignInActivity::class.java))
            context.finish()
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.SkyBlue)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.weight(1f))


            Image(
                painter = painterResource(id = R.drawable.ic_travelguide),
                contentDescription = "Travel Guide",
            )

            Spacer(modifier = Modifier.weight(1f))


        }
    }

}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}

fun gotoSignInActivity(context: Activity) {
    context.startActivity(Intent(context, SignInActivity::class.java))
    context.finish()
}