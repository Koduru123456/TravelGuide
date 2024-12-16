package com.hemanthS3083018.travelguide

import android.content.Context

object TravelGuideDetails {

    private const val PREFS_NAME = "TravelGuide"

    fun saveTravelguideuserStatus(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("TRAVELGUIDEUSER_STATUS", value).apply()
    }

    fun getTravelguideuserStatus(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("TRAVELGUIDEUSER_STATUS", false)
    }

    fun saveEmail(context: Context, email: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("TRAVELGUIDE_EMAIL", email).apply()
    }

    fun getEmail(context: Context): String? {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("TRAVELGUIDE_EMAIL", null)
    }
}