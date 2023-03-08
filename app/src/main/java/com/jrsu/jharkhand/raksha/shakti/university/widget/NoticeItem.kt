package com.jrsu.jharkhand.raksha.shakti.university.widget

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.jrsu.jharkhand.raksha.shakti.university.Objects.AppFont
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Colors
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Notice
import com.jrsu.jharkhand.raksha.shakti.university.R
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.T
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.Fire

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@Composable
fun NoticeItem(
    notice: Notice
){
    var img = R.drawable.link2
    var openClick by remember{ mutableStateOf(false)}
    when(notice.type){
        "URL" -> img  = R.drawable.link2
        "Image" -> img  = R.drawable.ic_image
        "PDF" -> img  = R.drawable.article
    }
    if(openClick)
        openClick = clickDialog(notice)

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(0.dp, 5.dp)
            .clickable {openClick = true},
        elevation = 0.8.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ){
        Column(
            Modifier.padding(10.dp)
        ){
            Row(
                Modifier.fillMaxWidth(),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ){
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(
                            fontSize = T.width(3f).sp,
                            fontFamily = AppFont.cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )){ append("By ") }
                        withStyle(SpanStyle(
                            fontSize = T.width(3f).sp,
                            fontFamily = AppFont.mPlus,
                            fontWeight = FontWeight.W600,
                            color = Colors.AppBlue
                        )){ append(notice.name) }
                    }
                )
                Text(
                    notice.time,
                    fontSize = T.width(3f).sp,
                    fontFamily = AppFont.mPlus,
                    fontWeight = FontWeight.W600,
                    color = Colors.AppBlue
                )
            }
            Spacer(Modifier.height(3.dp))
            Row(
                Modifier,
                Arrangement.Start,
                Alignment.CenterVertically
            ){
               Image(
                   rememberImagePainter(img),
               "",
                   modifier = Modifier
                       .size(T.width(10f).dp)
                       .padding(3.dp)
               )
               Text(
                   notice.title,
                   fontFamily = AppFont.mPlusMed,
                   fontSize = T.width(4.2f).sp,
                   fontWeight = FontWeight.W500
               )
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun clickDialog(notice: Notice) : Boolean{
    var dialog by remember{ mutableStateOf(true)}
    val context = LocalContext.current

    Dialog(onDismissRequest = { dialog = false },
        DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            Modifier
                .fillMaxSize().background(Color.DarkGray)
                .wrapContentSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        )   {
            when(notice.type){
                "URL" -> WEBView(notice.url)
                "Image" -> ZoomPhoto(notice.url)
                "PDF" -> {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(notice.url), "application/pdf")
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    try {
                        context.startActivity(intent)
                        //context.startActivity(Intent.createChooser(intent, "Open File"))
                    } catch (e: ActivityNotFoundException){
                        Toast.makeText(context, "App not fount to open PDF", Toast.LENGTH_SHORT).show()
                    }
                    dialog = false
                }
            }
        }
    }
    return dialog
}