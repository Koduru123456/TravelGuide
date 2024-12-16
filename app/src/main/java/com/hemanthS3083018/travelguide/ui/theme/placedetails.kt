package com.hemanthS3083018.travelguide.ui.theme

data class PlaceDetails(
    val placeName: String="",
    val category: String = "",
    val placeImage: Int=0,
    val placeLocation: String="",
    val openingHours: String="",
    val attractions: String="",
    val activities: String="",
    val contactNumberOne: String="",
    val contactNumberTwo: String="",
    val placeDescription: String="",

)

data class TravelGuideData(
 val fullname: String="",
    val email: String="",
    val country: String="",
    val password: String="",

)
