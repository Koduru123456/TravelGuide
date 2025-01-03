package hemanth.S3083018.travelguide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import hemanth.S3083018.travelguide.ui.theme.TravelGuideData
import hemanth.S3083018.travelguide.ui.theme.TravelGuideTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TravelGuideTheme {
                TravelGuideSignin()
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun TravelGuideSigninPreview() {
    TravelGuideSignin()
}


@Composable
fun TravelGuideSignin() {
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val context = LocalContext.current as Activity


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.SkyBlue))
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.travel_image),
            contentDescription = "Study Planner",
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = colorResource(id = R.color.SkyBlue))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.Gray, Color.Gray)),
                        shape = RoundedCornerShape(16.dp)
                    ),
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter Your Email") }
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.Gray, Color.Gray)),
                        shape = RoundedCornerShape(16.dp)
                    ),
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Your Password") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }
            Button(
                onClick = {
                    when {
                        email.isEmpty() -> {
                            Toast.makeText(context, " Please Enter Mail", Toast.LENGTH_SHORT).show()
                        }

                        password.isEmpty() -> {
                            Toast.makeText(context, " Please Enter Password", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> {
                            val userEmail = email
                            val userPassword = password

                            val database = FirebaseDatabase.getInstance()
                            val databaseReference = database.reference

                            databaseReference.child("Travellers").child(userEmail.replace(".", ","))
                                .get()
                                .addOnSuccessListener { snapshot ->
                                    if (snapshot.exists()) {
                                        val travelGuideData =
                                            snapshot.getValue(TravelGuideData::class.java)
                                        travelGuideData?.let {
                                            if (userPassword == it.password) {
                                                Toast.makeText(
                                                    context,
                                                    "Login Successfull",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                TravelGuideDetails.saveTravelguideuserStatus(
                                                    context,
                                                    true
                                                )
                                                TravelGuideDetails.saveTravellerEmail(
                                                    context,
                                                    userEmail
                                                )
                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        HomeActivity::class.java
                                                    )
                                                )
                                                context.finish()
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Incorrect Credentials",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    } else {
                                        Toast.makeText(context, "No User Found", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }.addOnFailureListener { exception ->
                                    println("Reached Exception")
                                }

                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.PureWhite),
                    contentColor = colorResource(
                        id = R.color.SkyBlue
                    )
                )
            ) {
                Text(text = "Sign In", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "You are a new tourist ?", fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign Up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.PureWhite),
                    modifier = Modifier.clickable {
                        context.startActivity(Intent(context, SignUpActivity::class.java))
                        context.finish()
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


        }
    }
}

