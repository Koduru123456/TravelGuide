package hemanth.S3083018.travelguide

import android.content.Context

object TravelGuideDetails {

    fun saveTravelguideuserStatus(context: Context, value: Boolean) {
        val statusPref = context.getSharedPreferences("TravelGuide", Context.MODE_PRIVATE)
        val editorStatus = statusPref.edit()
        editorStatus.putBoolean("STATUS_TRAVELGUIDEUSER", value).apply()
    }

    fun getTravelguideuserStatus(context: Context): Boolean {
        val statusPref = context.getSharedPreferences("TravelGuide", Context.MODE_PRIVATE)
        return statusPref.getBoolean("TRAVELGUIDEUSER_STATUS", false)
    }

    fun saveTravellerEmail(context: Context, email: String) {
        val emailPref = context.getSharedPreferences("TravelGuide", Context.MODE_PRIVATE)
        val editorEmail = emailPref.edit()
        editorEmail.putString("TRAVELGUIDE_EMAIL", email).apply()
    }

    fun getTravellerEmail(context: Context): String? {
        val emailPref = context.getSharedPreferences("TravelGuide", Context.MODE_PRIVATE)
        return emailPref.getString("TRAVELGUIDE_EMAIL", null)
    }
}