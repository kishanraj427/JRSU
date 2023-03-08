 package com.jrsu.jharkhand.raksha.shakti.university.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

 class T{
     companion object{
         @Composable
         fun height(s : Float) : Double{
             return s * LocalConfiguration.current.screenHeightDp.toDouble()/ 100
         }
         @Composable
         fun width(s: Float) : Double{
             return s * LocalConfiguration.current.screenWidthDp.toDouble() / 100
         }
     }
 }


//object Dimens{
//    val grid_0_25 = 2.dp
//    val grid_0_5 = 4.dp
//    val grid_1 = 8.dp
//    val grid_1_5 = 12.dp
//    val grid_2 = 16.dp
//    val grid_2_5 = 20.dp
//    val grid_3 = 24.dp
//    val grid_3_5 = 28.dp
//    val grid_4 = 32.dp
//    val grid_4_5 = 36.dp
//    val grid_5 = 40.dp
//    val grid_5_5 = 44.dp
//    val grid_6 = 48.dp
//}