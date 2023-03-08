package com.jrsu.jharkhand.raksha.shakti.university.widget

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.jrsu.jharkhand.raksha.shakti.university.Objects.AppFont
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Colors
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Syllabus
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.T
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.Fire

@Composable
fun SyllabusItem(syllabus: Syllabus){

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(0.dp, 5.dp)
            .clickable { openPDF(context, syllabus.url) },
        elevation = 3.dp,
        shape = RoundedCornerShape(15.dp)
    ){
        Box(
            Modifier.fillMaxSize()
            .background(Brush.verticalGradient(
                listOf(Colors.AppOrange, Colors.AppYellow),
                0.2f, 0.8f,
                TileMode.Repeated
            ))
        ){
        Text(
            syllabus.title,
            color = Color.White,
            fontFamily = AppFont.mPlusMed,
            fontSize = T.width(4.5f).sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(20.dp)
        )}
    }
}

fun openPDF(context: Context, url: String){
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(url), "application/pdf")
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    try {
        context.startActivity(intent)
        //context.startActivity(Intent.createChooser(intent, "Open File"))
    } catch (e: ActivityNotFoundException){
        Toast.makeText(context, "App not fount to open PDF", Toast.LENGTH_SHORT).show()
    }
}