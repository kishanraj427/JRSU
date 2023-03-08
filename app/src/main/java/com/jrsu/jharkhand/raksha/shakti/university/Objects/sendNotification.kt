package com.jrsu.jharkhand.raksha.shakti.university.Objects

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


@DelicateCoroutinesApi
suspend fun sendNotification(title: String, message: String, bigImage : String?) : Boolean {
    //Log.e("ImageUrl", bigImage.toString())
    var response = false
    withContext(Dispatchers.Default) {
        val apiKey = "MWQ0N2E0MDYtMTQ3ZC00MDhiLTg1NWUtOGRkNTA3ZDk3Zjll"
        val appId = "524d9166-7320-4874-93eb-2776e4f69e9b"
        val url = URL("https://onesignal.com/api/v1/notifications")
        val largeIcon = "https://firebasestorage.googleapis.com/v0/b/jrsu-653e8.appspot.com/o/jrsu_logo.png?alt=media&token=da36f96e-2f4d-4e78-9f1f-7e7265fcd96f"
        val con = url.openConnection() as HttpURLConnection
        con.useCaches = false
        con.doOutput = true
        con.doInput = true
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        con.setRequestProperty(
            "Authorization", "Basic $apiKey"
        )
        con.requestMethod = "POST"
        val strJsonBody = ("{" +
                "\"app_id\": \"$appId\"," +
                "\"included_segments\": [\"Subscribed Users\"]," +
                "\"isAndroid\": true," +
                "\"android_accent_color\": \"FFEB5C5C\"," +
                "\"large_icon\": \"$largeIcon\"," +
                "\"big_picture\": \"$bigImage\"," +
                "\"priority\": 10," +
                "\"headings\": {\"en\": \"$title\"}," +
                "\"contents\": {\"en\": \"$message\"}" +
                "}")
        val sendBytes = strJsonBody.toByteArray(charset("UTF-8"))
        con.setFixedLengthStreamingMode(sendBytes.size)
        try {
            val outputStream = BufferedWriter(OutputStreamWriter(con.outputStream, "UTF-8"))

            outputStream.write(strJsonBody)
            outputStream.flush()
            outputStream.close()
            val httpResponse = con.responseCode
            con.disconnect()
            response = (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST)


         //   Log.e("Responce", httpResponse.toString())

        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
    return response
}